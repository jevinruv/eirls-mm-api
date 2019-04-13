package com.jevin.eirlsmmapi.service;

import com.jevin.eirlsmmapi.model.ItemRaw;
import com.jevin.eirlsmmapi.repository.ItemRawRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemRawService {

    @Autowired
    ItemRawRepo itemRawRepo;

    public ResponseEntity<?> addOrUpdate(ItemRaw itemRaw) {

        itemRaw.getItemRawReorder().setItemRaw(itemRaw);
        itemRawRepo.save(itemRaw);
        return new ResponseEntity<>(itemRaw, HttpStatus.OK);
    }

}
