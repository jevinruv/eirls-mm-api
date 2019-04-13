package com.jevin.eirlsmmapi.controller;

import com.jevin.eirlsmmapi.model.ItemComplete;
import com.jevin.eirlsmmapi.repository.ItemCompleteRepo;
import com.jevin.eirlsmmapi.service.ItemCompleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items-complete")
public class ItemCompleteController {

    @Autowired
    ItemCompleteService itemCompleteService;

    @Autowired
    ItemCompleteRepo itemCompleteRepo;

    @GetMapping("/{id}")
    public Optional<ItemComplete> get(@PathVariable int id) {
        return itemCompleteRepo.findById(id);
    }

    @GetMapping
    public List<ItemComplete> getAll() {
        return itemCompleteRepo.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('MM')")
    public ResponseEntity<?> addOrUpdate(@RequestBody ItemComplete itemComplete) {
        return itemCompleteService.addOrUpdate(itemComplete);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MM') or hasRole('ADMIN')")
    public void delete(@PathVariable int id) {
        itemCompleteRepo.deleteById(id);
    }
}
