package com.jevin.eirlsmmapi.controller;

import com.jevin.eirlsmmapi.model.RawItem;
import com.jevin.eirlsmmapi.repository.RawItemRepo;
import com.jevin.eirlsmmapi.service.RawItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/raw-items")
public class RawItemController {

    @Autowired
    RawItemService rawItemService;

    @Autowired
    RawItemRepo rawItemRepo;

    @GetMapping("/{id}")
    public Optional<RawItem> get(@PathVariable int id) {
        return rawItemRepo.findById(id);
    }

    @GetMapping
    public List<RawItem> getAll() {
        return rawItemRepo.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('MM')")
    public ResponseEntity<?> addOrUpdate(@RequestBody RawItem rawItem) {
        return rawItemService.addOrUpdate(rawItem);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MM') or hasRole('ADMIN')")
    public void delete(@PathVariable int id) {
        rawItemRepo.deleteById(id);
    }
}
