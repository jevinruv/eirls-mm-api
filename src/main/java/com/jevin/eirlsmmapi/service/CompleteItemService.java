package com.jevin.eirlsmmapi.service;

import com.jevin.eirlsmmapi.model.CompleteItem;
import com.jevin.eirlsmmapi.repository.CompleteItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CompleteItemService {

    @Autowired
    CompleteItemRepo completeItemRepo;

    public ResponseEntity<?> addOrUpdate(CompleteItem completeItem) {

        completeItem.getCompleteItemReorder().setCompleteItem(completeItem);
        completeItemRepo.save(completeItem);
        return new ResponseEntity<>(completeItem, HttpStatus.OK);
    }

}
