package com.jevin.eirlsmmapi.service;

import com.jevin.eirlsmmapi.model.ItemRaw;
import com.jevin.eirlsmmapi.repository.ItemRawRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemRawService {

    @Autowired
    ItemRawRepo itemRawRepo;

    public ResponseEntity<?> addOrUpdate(ItemRaw itemRaw) {

        itemRaw.getItemRawReorder().setItemRaw(itemRaw);
        itemRawRepo.save(itemRaw);
        return new ResponseEntity<>(itemRaw, HttpStatus.OK);
    }

    public List<ItemRaw> getItemRawLowQuantities() {

        List<ItemRaw> itemRawReorderList = new ArrayList<>();

        List<ItemRaw> itemRawList = itemRawRepo.findAll();

        itemRawList.forEach(rawItem -> {

            if (rawItem.getQuantity() <= rawItem.getItemRawReorder().getLevel()) {
                itemRawReorderList.add(rawItem);
            }
        });

        return itemRawReorderList;
    }

}
