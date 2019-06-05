package com.jevin.eirlsmmapi.controller;

import com.jevin.eirlsmmapi.model.SalesOrder;
import com.jevin.eirlsmmapi.repository.SalesOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sales-orders")
public class SalesOrderController {

    @Autowired
    SalesOrderRepo salesOrderRepo;


    @GetMapping("/{id}")
    public Optional<SalesOrder> get(@PathVariable int id) {
        return salesOrderRepo.findById(id);
    }

    @GetMapping
    public List<SalesOrder> getAll() {
        return salesOrderRepo.findAll();
    }

}
