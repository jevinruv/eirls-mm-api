package com.jevin.eirlsmmapi.service;

import com.jevin.eirlsmmapi.exception.ResourceNotFoundException;
import com.jevin.eirlsmmapi.form.SupplierOrderForm;
import com.jevin.eirlsmmapi.model.ItemRaw;
import com.jevin.eirlsmmapi.model.SupplierOrder;
import com.jevin.eirlsmmapi.model.SupplierOrderItem;
import com.jevin.eirlsmmapi.repository.ItemRawRepo;
import com.jevin.eirlsmmapi.repository.SupplierOrderItemRepo;
import com.jevin.eirlsmmapi.repository.SupplierOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class SupplierOrderService {

    @Autowired
    ItemRawRepo itemRawRepo;

    @Autowired
    SupplierOrderRepo supplierOrderRepo;

    @Autowired
    SupplierOrderItemRepo supplierOrderItemRepo;

    SupplierOrderItem supplierOrderItem = null;


    public ResponseEntity<?> addOrUpdate(SupplierOrderForm supplierOrderForm) {

        ItemRaw itemRaw = itemRawRepo
                .findById(supplierOrderForm.getItemRawId())
                .orElseThrow(() -> new ResourceNotFoundException("Item Raw not found for this id :: " + supplierOrderForm.getItemRawId()));

        SupplierOrder supplierOrder = supplierOrderRepo
                .findById(supplierOrderForm.getSupplierOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier Order not found for this id :: " + supplierOrderForm.getSupplierOrderId()));

        Optional<SupplierOrderItem> supplierOrderItemOptional = supplierOrderItemRepo
                .findByItemRawIdAndSupplierOrderId(supplierOrderForm.getItemRawId(), supplierOrderForm.getSupplierOrderId());

        if (supplierOrderItemOptional.isPresent()) {
            update(supplierOrderForm, supplierOrderItemOptional);
        } else {
            add(supplierOrderForm, supplierOrder, itemRaw);
        }

        return new ResponseEntity<>(this.supplierOrderItem, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteOrder(int id) {

        supplierOrderRepo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier Order not found for this id :: " + id));

        supplierOrderRepo.deleteById(id);

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteOrderItem(SupplierOrderForm supplierOrderForm) {

        this.supplierOrderItem = supplierOrderItemRepo
                .findByItemRawIdAndSupplierOrderId(supplierOrderForm.getItemRawId(), supplierOrderForm.getSupplierOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier Order Item not found for this id :: " + supplierOrderForm.getSupplierOrderId()));

        supplierOrderItemRepo.deleteById(this.supplierOrderItem.getId());

        return new ResponseEntity<>(this.supplierOrderItem, HttpStatus.OK);
    }

    public ResponseEntity<?> newSupplierOrder() {

        SupplierOrder supplierOrder = new SupplierOrder();


        supplierOrder.setCreatedDate(getDate());
        supplierOrder = supplierOrderRepo.save(supplierOrder);

        return new ResponseEntity<>(supplierOrder, HttpStatus.OK);
    }

    private void add(SupplierOrderForm supplierOrderForm, SupplierOrder supplierOrder, ItemRaw itemRaw) {

        supplierOrderItem = new SupplierOrderItem();
        supplierOrderItem.setQuantity(supplierOrderForm.getQuantity());
        supplierOrderItem.setSupplierOrder(supplierOrder);
        supplierOrderItem.setItemRaw(itemRaw);

        supplierOrderItemRepo.save(supplierOrderItem);
    }

    private void update(SupplierOrderForm supplierOrderForm, Optional<SupplierOrderItem> supplierOrderItemOptional) {

        supplierOrderItem = supplierOrderItemOptional.get();
        supplierOrderItem.setQuantity(supplierOrderForm.getQuantity());

        supplierOrderItemRepo.save(supplierOrderItem);
    }

    private Date getDate() {

        Date date = new Date();

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
//        df.setTimeZone(TimeZone.getTimeZone("Asia/Colombo"));

        String strDate = df.format(date);

        Date newDate = null;
        try {
            newDate = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return newDate;
    }

}



