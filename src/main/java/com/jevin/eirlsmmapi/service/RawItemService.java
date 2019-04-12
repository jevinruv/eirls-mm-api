package com.jevin.eirlsmmapi.service;

import com.jevin.eirlsmmapi.model.RawItem;
import com.jevin.eirlsmmapi.repository.RawItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RawItemService {

    @Autowired
    RawItemRepo repo;

    public ResponseEntity<?> addOrUpdate(RawItem rawItem) {

        rawItem.getRawItemReorder().setRawItem(rawItem);
        repo.save(rawItem);
        return new ResponseEntity<>(rawItem, HttpStatus.OK);
    }

}
