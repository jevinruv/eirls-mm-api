package com.jevin.eirlsmmapi.controller;

import com.jevin.eirlsmmapi.model.Item;
import com.jevin.eirlsmmapi.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @PreAuthorize("hasRole('MM')")
    public Item addOrUpdate(@RequestBody Item item) {
        return repo.save(item);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MM') or hasRole('ADMIN')")
    public void delete(@PathVariable int id) {
        repo.deleteById(id);
    }
}
