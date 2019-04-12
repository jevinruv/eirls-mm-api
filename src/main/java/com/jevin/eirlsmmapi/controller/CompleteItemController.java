package com.jevin.eirlsmmapi.controller;

import com.jevin.eirlsmmapi.model.CompleteItem;
import com.jevin.eirlsmmapi.repository.CompleteItemRepo;
import com.jevin.eirlsmmapi.service.CompleteItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/complete-items")
public class CompleteItemController {

    @Autowired
    CompleteItemService completeItemService;

    @Autowired
    CompleteItemRepo completeItemRepo;

    @GetMapping("/{id}")
    public Optional<CompleteItem> get(@PathVariable int id) {
        return completeItemRepo.findById(id);
    }

    @GetMapping
    public List<CompleteItem> getAll() {
        return completeItemRepo.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('MM')")
    public ResponseEntity<?> addOrUpdate(@RequestBody CompleteItem completeItem) {
        return completeItemService.addOrUpdate(completeItem);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MM') or hasRole('ADMIN')")
    public void delete(@PathVariable int id) {
        completeItemRepo.deleteById(id);
    }
}
