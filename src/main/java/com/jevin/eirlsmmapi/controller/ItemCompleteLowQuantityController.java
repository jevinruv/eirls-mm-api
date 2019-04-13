package com.jevin.eirlsmmapi.controller;

import com.jevin.eirlsmmapi.model.ItemCompleteLowQuantity;
import com.jevin.eirlsmmapi.repository.ItemCompleteLowQuantityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items-complete-low-quantity")
public class ItemCompleteLowQuantityController {

    @Autowired
    ItemCompleteLowQuantityRepo itemCompleteLowQuantityRepo;

    @GetMapping("/{id}")
    public Optional<ItemCompleteLowQuantity> get(@PathVariable int id) {
        return itemCompleteLowQuantityRepo.findById(id);
    }

    @GetMapping
    public List<ItemCompleteLowQuantity> getAll() {
        return itemCompleteLowQuantityRepo.findAll();
    }

}
