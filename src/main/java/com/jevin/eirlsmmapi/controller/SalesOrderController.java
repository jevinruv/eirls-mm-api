package com.jevin.eirlsmmapi.controller;

import com.jevin.eirlsmmapi.exception.ResourceNotFoundException;
import com.jevin.eirlsmmapi.form.SalesOrderStatusForm;
import com.jevin.eirlsmmapi.model.SalesOrder;
import com.jevin.eirlsmmapi.repository.SalesOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public ResponseEntity<?> updateStatus(@RequestBody SalesOrderStatusForm salesOrderStatusForm) {

        SalesOrder salesOrder = salesOrderRepo
                .findBySalesOrderId(salesOrderStatusForm.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Sales Order not found for this id or Invalid :: " + salesOrderStatusForm.getId()));

        if (!salesOrderStatusForm.getStatus().equals("confirmed")) {
            throw new ResourceNotFoundException("Sales Order Status Invalid :: " + salesOrderStatusForm.getStatus());
        }

        salesOrder.setStatus(salesOrderStatusForm.getStatus());
        SalesOrder savedSalesOrder = salesOrderRepo.save(salesOrder);

        return new ResponseEntity<>(savedSalesOrder, HttpStatus.OK);
    }

}
