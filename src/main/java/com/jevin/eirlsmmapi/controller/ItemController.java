package com.jevin.eirlsmmapi.controller;

import com.jevin.eirlsmmapi.model.Item;
import com.jevin.eirlsmmapi.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemRepo repo;

    @GetMapping("/{id}")
    public Optional<Item> get(@PathVariable int id) {
        return repo.findById(id);
    }

    @GetMapping
    public List<Item> getAll() {
        return repo.findAll();
    }
}
