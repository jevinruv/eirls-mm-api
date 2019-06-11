package com.jevin.eirlsmmapi.service;

import com.jevin.eirlsmmapi.exception.ResourceNotFoundException;
import com.jevin.eirlsmmapi.form.SupplierOrderReceivedForm;
import com.jevin.eirlsmmapi.model.ItemRaw;
import com.jevin.eirlsmmapi.model.SupplierOrder;
import com.jevin.eirlsmmapi.repository.ItemRawRepo;
import com.jevin.eirlsmmapi.repository.SupplierOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GoodReceivedService {

    @Autowired
    SupplierOrderRepo supplierOrderRepo;

    @Autowired
    ItemRawRepo itemRawRepo;

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

        if (counter == supplierOrder.getSupplierOrderItems().size()) {
            supplierOrder.setStatus("RECEIVED");
            supplierOrderRepo.save(supplierOrder);
        } else {
            throw new ResourceNotFoundException("Supplier Order items quantity does not match, required " + supplierOrder.getSupplierOrderItems().size() + " got " + counter);
        }

    }

    public SupplierOrder addToStock(int id) {

        SupplierOrder supplierOrder = supplierOrderRepo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier Order not found for this id or Invalid :: " + id));

        supplierOrder.getSupplierOrderItems().forEach(supplierOrderItem -> {

            ItemRaw itemRaw = itemRawRepo
                    .findById(supplierOrderItem.getItemRaw().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("ItemRaw not found for this id or Invalid :: " + id));

            int quantityReceived = supplierOrderItem.getQuantityReceived();
            int quantityCurrent = itemRaw.getQuantity();
            int QuantityNew = quantityCurrent + quantityReceived;

            itemRaw.setQuantity(QuantityNew);
            itemRawRepo.save(itemRaw);

        });

        supplierOrder.setStatus("ADDED");
        return supplierOrderRepo.save(supplierOrder);
    }

}
