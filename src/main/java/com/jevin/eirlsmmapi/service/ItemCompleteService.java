package com.jevin.eirlsmmapi.service;

import com.jevin.eirlsmmapi.model.ItemComplete;
import com.jevin.eirlsmmapi.repository.ItemCompleteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemCompleteService {

    @Autowired
    ItemCompleteRepo itemCompleteRepo;

    public ResponseEntity<?> addOrUpdate(ItemComplete itemComplete) {

        itemComplete.getItemCompleteReorder().setItemComplete(itemComplete);
        itemCompleteRepo.save(itemComplete);
        return new ResponseEntity<>(itemComplete, HttpStatus.OK);
    }

}
