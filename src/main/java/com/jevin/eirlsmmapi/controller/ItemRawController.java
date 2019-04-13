package com.jevin.eirlsmmapi.controller;

import com.jevin.eirlsmmapi.model.ItemRaw;
import com.jevin.eirlsmmapi.repository.ItemRawRepo;
import com.jevin.eirlsmmapi.service.ItemRawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items-raw")
public class ItemRawController {

    @Autowired
    ItemRawService itemRawService;

    @Autowired
    ItemRawRepo itemRawRepo;

    @GetMapping("/{id}")
    public Optional<ItemRaw> get(@PathVariable int id) {
        return itemRawRepo.findById(id);
    }

    @GetMapping
    public List<ItemRaw> getAll() {
        return itemRawRepo.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('MM')")
    public ResponseEntity<?> addOrUpdate(@RequestBody ItemRaw itemRaw) {
        return itemRawService.addOrUpdate(itemRaw);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MM') or hasRole('ADMIN')")
    public void delete(@PathVariable int id) {
        itemRawRepo.deleteById(id);
    }
}
