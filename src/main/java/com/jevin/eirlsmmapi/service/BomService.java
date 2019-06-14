package com.jevin.eirlsmmapi.service;

import com.jevin.eirlsmmapi.form.Bom;
import com.jevin.eirlsmmapi.form.BomItem;
import com.jevin.eirlsmmapi.model.ItemRaw;
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

    final String bomUrl = "https://eirlss-production.herokuapp.com/public/api/billofmaterials";

    private String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImRmZmIwZDQ4NjMxODg1OTBmZDEyZTlhMDUxYTkxY2FkNWJlNTk5NGFiZmJiNTEzOTAxYmU4ZWZkMWU3MDFiNThlZGM4NTIyMGE0NGQzNjc2In0.eyJhdWQiOiIzIiwianRpIjoiZGZmYjBkNDg2MzE4ODU5MGZkMTJlOWEwNTFhOTFjYWQ1YmU1OTk0YWJmYmI1MTM5MDFiZThlZmQxZTcwMWI1OGVkYzg1MjIwYTQ0ZDM2NzYiLCJpYXQiOjE1NjA0NDMwNTEsIm5iZiI6MTU2MDQ0MzA1MSwiZXhwIjoxNTkyMDY1NDUxLCJzdWIiOiIiLCJzY29wZXMiOlsiKiJdfQ.RTb1XAtAJo32DEen06rAAzKqCdD0doB3yFbkMC1xzWhlOrKsfBxJrQXzCkLMWNXeWsSyGnGqDKmOj-rExEBGy2usIj334_PBYChVU5f08B5DtYfVduBlNWTdb6vCX4u13bq3yeasSN574fj7Hg4U7hFZx9AIowuV8WMAxd2F8Veg0ty0PxEwYn4xjQl6tUrfjbwOrFAdc19fI754FZhEYzkGdtfr_oDdCBNHlz1_Gx_GQboJPYCEJ6uE7jsvh7C9gtRwM3BsNdtOBwjDNi9zy8YYvzDyb2q2AKbjm2AUQ1jR_m-gLzs1h1x6zerqCwPMxUeg2xSTKDET33IwAuzgBEJqlZGcd9fYmJklV9RXoyz-XpjLHyLv9jDKiQdE6CjpzSYzFjcMsa_0mqIc2zcBT-hcZA4vs4Ttg_977yYZHmP3UioxIv_A0uh2rQElPsn-4Udcmf7t-qEbmfhh54BKZGDzr_dBHOnLOtEA46OL1xUh7eRSio2BxKOIjmW0ptUdG9s2ew9w8UBxFe_jKmtXvD_BFXBzVNVYu1jWcJyTgSHNimiJwLSmFi3jfYD8p9LYWizGEW4uABBQ-QjsqbJ1z1gNXv9ijtUE6O2f8GWJApWWd2Cr-LFPH35rzUn3FeLHCS6A8zNWe2VW4BTzxz5dOatP48J_X-zlZs_iIwsXsNc";

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

    public void validateOrder(List<Bom> bomList) {

        bomList.forEach(bom -> {

            if (bom.getStatus() == 1) {
                checkQuantity(bom);
            }

        });

    }

    private void checkQuantity(Bom bom) {

        bom.getBomItemList().forEach(bomItem -> {

            Optional<ItemRaw> itemRawOptional = itemRawRepo.findById(bomItem.getItemRawId());

            if (itemRawOptional.isPresent()) {

                ItemRaw itemRaw = itemRawOptional.get();

                if (itemRaw.getQuantity() > bomItem.getQuantity()) {
                    counter++;
                }
            }

        });

        if (counter == bom.getBomItemList().size()) {
            reduceItems(bom.getBomItemList());
            updateStatus(bom);
        }

    }

    private void reduceItems(List<BomItem> bomItemList) {

        bomItemList.forEach(bomItem -> {
            int quantity = bomItem.getQuantity();
            int id = bomItem.getItemRawId();

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
        ResponseEntity<Object> result = template.exchange(bomUrl + bom.getBomId(), HttpMethod.PUT, requestEntity, Object.class);

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
