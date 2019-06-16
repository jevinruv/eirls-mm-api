package com.jevin.eirlsmmapi.controller;

import com.jevin.eirlsmmapi.model.Bom;
import com.jevin.eirlsmmapi.repository.BomRepo;
import com.jevin.eirlsmmapi.service.BomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boms")
public class BomController {

    @Autowired
    BomRepo bomRepo;

    @Autowired
    BomService bomService;


    @GetMapping("/{id}")
    public Optional<Bom> get(@PathVariable int id) {
        return bomRepo.findById(id);
    }

    @GetMapping
    public List<Bom> getAll() {
        return bomRepo.findAll();
    }

    @GetMapping("/availability/{id}")
    public ResponseEntity<?> getAvailability(@PathVariable int id) {

        boolean isAvailable = bomService.checkAvailability(id);

        return new ResponseEntity<>(isAvailable, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable int id) {

        Bom bom = bomService.updateBOM(id);

        return new ResponseEntity<>(bom, HttpStatus.OK);
    }

}
