package com.jevin.eirlsmmapi.controller;

import com.jevin.eirlsmmapi.model.ItemRawLowQuantity;
import com.jevin.eirlsmmapi.repository.ItemRawLowQuantityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items-raw-low-quantity")
public class ItemRawLowQuantityController {

    @Autowired
    ItemRawLowQuantityRepo itemRawLowQuantityRepo;

    @GetMapping("/{id}")
    public Optional<ItemRawLowQuantity> get(@PathVariable int id) {
        return itemRawLowQuantityRepo.findById(id);
    }

    @GetMapping
    public List<ItemRawLowQuantity> getAll() {
        return itemRawLowQuantityRepo.findAll();
    }

}
