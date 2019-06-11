package com.jevin.eirlsmmapi.controller;

import com.jevin.eirlsmmapi.form.SupplierOrderReceivedForm;
import com.jevin.eirlsmmapi.model.SupplierOrder;
import com.jevin.eirlsmmapi.service.GoodReceivedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods-received")
public class GoodsReceivedController {

    @Autowired
    GoodReceivedService goodReceivedService;


    @PostMapping
    public ResponseEntity<?> addReceived(@RequestBody SupplierOrderReceivedForm supplierOrderReceivedForm) {
        goodReceivedService.add(supplierOrderReceivedForm);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/add-stock/{id}")
    public ResponseEntity<?> addToStock(@PathVariable int id) {
        SupplierOrder supplierOrder = goodReceivedService.addToStock(id);
        return new ResponseEntity<>(supplierOrder, HttpStatus.OK);
    }

}
