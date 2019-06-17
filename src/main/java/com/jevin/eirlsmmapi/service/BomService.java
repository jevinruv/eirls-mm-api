package com.jevin.eirlsmmapi.service;

import com.jevin.eirlsmmapi.exception.ResourceNotFoundException;
import com.jevin.eirlsmmapi.form.TokenProduction;
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

    final String urlBom = "https://eirlss-production.herokuapp.com/public/api/billofmaterials";
    final String urlToken = "http://eirlss-production.herokuapp.com/public/api/oauth/token";

    private String token = null;

    private int counter = 0;

    private HttpEntity<String> entity;


    public List<Bom> getBoms() {

        getToken();
        getRestClient();

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Bom[]> bomResponseEntity = restTemplate.exchange(urlBom + "?includes=billItems.material", HttpMethod.GET, entity, Bom[].class);

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

    public boolean checkAvailability(int id) {

        Bom bom = bomRepo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BOM not found for this id :: " + id));

        counter = 0;

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
            return true;
        }

        return false;
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

    public Bom updateBOM(int id) {

        Bom bom = bomRepo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BOM not found for this id :: " + id));

        reduceItems(bom.getBomItems());

        updateStatus(bom);

        bom.setStatus("Approved");
        return bomRepo.save(bom);
    }

    private void updateStatus(Bom bom) {

        getToken();
        RestTemplate template = new RestTemplate();
        Map payload = new HashMap<String, String>();

        payload.put("status", "Approved");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map> requestEntity = new HttpEntity<>(payload, headers);
        ResponseEntity<Object> result = template.exchange(urlBom + "/" + bom.getProductionBomId(), HttpMethod.PUT, requestEntity, Object.class);

        if (!result.getStatusCode().is2xxSuccessful()) {
//            return null;
        }
    }

    private void getRestClient() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + token);
        entity = new HttpEntity<String>("parameters", headers);
    }

    private void getToken() {

        RestTemplate restTemplate = new RestTemplate();

        Map payload = new HashMap<String, String>();

        payload.put("grant_type", "client_credentials");
        payload.put("scope", "*");
        payload.put("client_id", "3");
        payload.put("client_secret", "41xadAeKIUtiQtkgVmMixurfWengoamkDUlcoE6L");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map> requestEntity = new HttpEntity<>(payload, headers);
        ResponseEntity<TokenProduction> result = restTemplate.exchange(urlToken, HttpMethod.POST, requestEntity, TokenProduction.class);

        if (!result.getStatusCode().is2xxSuccessful()) {
//            return null;
        }

        TokenProduction tokenProduction = result.getBody();
        token = tokenProduction.getAccess_token();
    }


}
