package com.jevin.eirlsmmapi.service;

import com.jevin.eirlsmmapi.exception.ResourceNotFoundException;
import com.jevin.eirlsmmapi.form.SupplierOrderReceivedForm;
import com.jevin.eirlsmmapi.model.SupplierOrder;
import com.jevin.eirlsmmapi.repository.SupplierOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodReceivedService {

    @Autowired
    SupplierOrderRepo supplierOrderRepo;
    private int counter = 0;


    public void add(SupplierOrderReceivedForm supplierOrderReceivedForm) {

        SupplierOrder supplierOrder = supplierOrderRepo
                .findByIdAndStatus(supplierOrderReceivedForm.getSupplierOrderId(), "SENT")
                .orElseThrow(() -> new ResourceNotFoundException("Supplier Order not found for this id or Invalid :: " + supplierOrderReceivedForm.getSupplierOrderId()));


        counter = 0;

        supplierOrderReceivedForm.getSupplierOrderItems().forEach(supplierOrderItemReceivedForm -> {

            supplierOrder.getSupplierOrderItems().forEach(supplierOrderItem -> {

                if (supplierOrderItemReceivedForm.getItemRawId() == supplierOrderItem.getItemRaw().getId()) {
                    supplierOrderItem.setQuantityReceived(supplierOrderItemReceivedForm.getQuantityReceived());
                    counter++;
                }

            });
        });

        if (counter == supplierOrderReceivedForm.getSupplierOrderItems().size()) {
            supplierOrder.setStatus("RECEIVED");
            supplierOrderRepo.save(supplierOrder);
        }

    }

}
