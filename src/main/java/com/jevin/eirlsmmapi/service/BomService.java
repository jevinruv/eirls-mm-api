package com.jevin.eirlsmmapi.service;

import com.jevin.eirlsmmapi.model.Bom;
import com.jevin.eirlsmmapi.model.BomItem;
import com.jevin.eirlsmmapi.model.ItemRaw;
import com.jevin.eirlsmmapi.repository.BomRepo;
import com.jevin.eirlsmmapi.repository.ItemRawRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class BomService {


    @Autowired
    ItemRawRepo itemRawRepo;

    @Autowired
    BomRepo bomRepo;

    final String bomUrl = "https://eirlss-production.herokuapp.com/public/api/billofmaterials";

    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6Ijg0MmI1NWE2YjMwMmIwNDZjZjkyZTZiYjA0ZjJhOTg0OTYzNmMxMzViYTczYjA1ZGU1ZmQxZmZlM2U3MzllZjk1ZTJkYzlhZTBjYjU2ZWY5In0.eyJhdWQiOiIzIiwianRpIjoiODQyYjU1YTZiMzAyYjA0NmNmOTJlNmJiMDRmMmE5ODQ5NjM2YzEzNWJhNzNiMDVkZTVmZDFmZmUzZTczOWVmOTVlMmRjOWFlMGNiNTZlZjkiLCJpYXQiOjE1NjA1MDEwODgsIm5iZiI6MTU2MDUwMTA4OCwiZXhwIjoxNTkyMTIzNDg4LCJzdWIiOiIiLCJzY29wZXMiOlsiKiJdfQ.UTMkwNzMatnZPiLzkl3rexFidYTIvMPv5pUs_GHz8YT_pVYMd3M08505R9rB2VugKghX-IMNtrYUqru1A-dk4YkSSXA-PgNyf7T4pqtSnT7k17dXpABKR7jlu2l_27MyOARP_E67HhXeITLdYGZSmvk7ruHxMfNrQ-YFJP8k253TT80Ly9Q89gK3TAoTG9SmwG42sgYeJ4q320XXrNBDffQaXoL_aomSJCUrms7uFIyaIMfcv9KgTgpvK0r0KNtO5nMlxvTYJzw4FjMAdCBYTPyPYQa8YhW5wnfgqgkaOgMuwJUo8kXbAorOSObeSBFOQ5ApozgxU0oxLy6ep5MyVU0OE6BuH1oZ9aZALA3x5hijcN1wVcmOWDu5-XW3YUa-wBDkYWI_MPj-1LLDigzBj8XtVfN-z64hX8hrvRZrk4dDg7UaX9TKyepPzstEoUC9hLZtLGgXWRi1O85TjgXvqa8p22TKGDJ6MDj7gz7UKK7rmiozeKyhDJwKt0XSlpfCH-gCGCXYfXW17kYZiOZ8sy7AYplPhGN674HxNRbHssdW07dH_42yjepb7jyIKlJFWsc1PBJyGYz_hQJ3s0VymKGGjpqa0IqrFFX8QLEqlDlj8agVeJouTCRttxtgf8b5mjDK0apAjV-t5vLPvipVPwSdIEY4kOEkdnpp5OTYK24";

    private int counter = 0;

    private HttpEntity<String> entity;


    public List<Bom> getBoms() {

        getRestClient();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Bom[]> bomResponseEntity = restTemplate.exchange(bomUrl + "?includes=billItems.material", HttpMethod.GET, entity, Bom[].class);

        if (!bomResponseEntity.getStatusCode().is2xxSuccessful()) {
            return null;
        }

        Bom[] boms = bomResponseEntity.getBody();
        List<Bom> bomList = Arrays.asList(boms);

        return bomList;
    }

    public void createBom(List<Bom> bomList) {

        bomList.forEach(bom -> {

            Optional<Bom> bomOptional = bomRepo.findByProductionBomId(bom.getProductionBomId());

            if (!bomOptional.isPresent()) {
                bomRepo.save(bom);
            }
        });

    }

    public void validateOrder(List<Bom> bomList) {

        bomList.forEach(bom -> {

            if (bom.getStatus() == 1) {
                checkQuantity(bom);
            }

        });

    }

    private void checkQuantity(Bom bom) {

        bom.getBomItems().forEach(bomItem -> {

            Optional<ItemRaw> itemRawOptional = itemRawRepo.findById(bomItem.getItemRaw().getId());

            if (itemRawOptional.isPresent()) {

                ItemRaw itemRaw = itemRawOptional.get();

                if (itemRaw.getQuantity() > bomItem.getQuantity()) {
                    counter++;
                }
            }

        });

        if (counter == bom.getBomItems().size()) {
            reduceItems(bom.getBomItems());
            updateStatus(bom);
        }

    }

    private void reduceItems(List<BomItem> bomItemList) {

        bomItemList.forEach(bomItem -> {
            int quantity = bomItem.getQuantity();
            int id = bomItem.getItemRaw().getId();

            ItemRaw itemRaw = itemRawRepo.findById(id).get();
            int currentQuantity = itemRaw.getQuantity();
            itemRaw.setQuantity(currentQuantity - quantity);

            itemRawRepo.save(itemRaw);
        });
    }

    private void updateStatus(Bom bom) {

        RestTemplate template = new RestTemplate();
        Map payload = new HashMap<String, String>();

        payload.put("status", "Approved");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map> requestEntity = new HttpEntity<>(payload, headers);
        ResponseEntity<Object> result = template.exchange(bomUrl + bom.getProductionBomId(), HttpMethod.PUT, requestEntity, Object.class);

        if (!result.getStatusCode().is2xxSuccessful()) {
//            return null;
        }
    }

    private void getRestClient() {

//        String url = "http://eirlss-production.herokuapp.com/public/api/oauth/token";
//        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        entity = new HttpEntity<String>("parameters", headers);
    }


}
