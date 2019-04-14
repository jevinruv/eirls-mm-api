package com.jevin.eirlsmmapi.controller;

import com.jevin.eirlsmmapi.form.SupplierOrderForm;
import com.jevin.eirlsmmapi.model.SupplierOrder;
import com.jevin.eirlsmmapi.repository.SupplierOrderRepo;
import com.jevin.eirlsmmapi.service.SupplierOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/supplier-orders")
public class SupplierOrderController {

    @Autowired
    SupplierOrderRepo repo;

    @Autowired
    SupplierOrderService supplierOrderService;

    @GetMapping("/{id}")
    public Optional<SupplierOrder> get(@PathVariable int id) {
        return repo.findById(id);
    }

    @GetMapping
    public List<SupplierOrder> getAll() {
        return repo.findAll();
    }

    @PostMapping("/new")
    public ResponseEntity<?> fetchCart() {
        return supplierOrderService.newSupplierOrder();
    }

    @PostMapping
    public ResponseEntity<?> addOrUpdate(@RequestBody SupplierOrderForm supplierOrderForm) {
        return supplierOrderService.addOrUpdate(supplierOrderForm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable int id) {
        return supplierOrderService.deleteOrder(id);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteOrderItem(@RequestBody SupplierOrderForm supplierOrderForm) {
        return supplierOrderService.deleteOrderItem(supplierOrderForm);
    }


}
