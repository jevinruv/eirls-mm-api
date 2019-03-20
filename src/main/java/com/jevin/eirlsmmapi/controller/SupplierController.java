package com.jevin.eirlsmmapi.controller;

import com.jevin.eirlsmmapi.model.Supplier;
import com.jevin.eirlsmmapi.repository.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    SupplierRepo repo;

    @GetMapping("/{id}")
    public Optional<Supplier> get(@PathVariable int id) {
        return repo.findById(id);
    }


    @GetMapping
    public List<Supplier> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public Supplier add(@RequestBody Supplier supplier) {
        return repo.save(supplier);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        repo.deleteById(id);
    }
}
