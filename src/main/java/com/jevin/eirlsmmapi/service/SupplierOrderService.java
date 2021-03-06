package com.jevin.eirlsmmapi.service;

import com.jevin.eirlsmmapi.exception.ResourceNotFoundException;
import com.jevin.eirlsmmapi.form.SupplierOrderForm;
import com.jevin.eirlsmmapi.model.ItemRaw;
import com.jevin.eirlsmmapi.model.Supplier;
import com.jevin.eirlsmmapi.model.SupplierOrder;
import com.jevin.eirlsmmapi.model.SupplierOrderItem;
import com.jevin.eirlsmmapi.repository.ItemRawRepo;
import com.jevin.eirlsmmapi.repository.SupplierOrderItemRepo;
import com.jevin.eirlsmmapi.repository.SupplierOrderRepo;
import com.jevin.eirlsmmapi.repository.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SupplierOrderService {

    @Autowired
    ItemRawRepo itemRawRepo;

    @Autowired
    SupplierRepo supplierRepo;

    @Autowired
    SupplierOrderRepo supplierOrderRepo;

    @Autowired
    SupplierOrderItemRepo supplierOrderItemRepo;

    SupplierOrder supplierOrder = null;


    public SupplierOrder addOrUpdate(SupplierOrderForm supplierOrderForm) {

        Supplier supplier = supplierRepo
                .findById(supplierOrderForm.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found for this id :: " + supplierOrderForm.getSupplierId()));

        if (supplierOrderForm.getId() != 0) {
            supplierOrder = update(supplierOrderForm, supplier);
        } else {
            supplierOrder = add(supplierOrderForm, supplier);
        }

        return supplierOrder;
    }

    private SupplierOrder add(SupplierOrderForm supplierOrderForm, Supplier supplier) {

        Set<SupplierOrderItem> supplierOrderItems = new HashSet<>();

        supplierOrderForm.getSupplierOrderItems().forEach(supplierOrderItemForm -> {

            supplier.getItemsRaw().forEach(itemRaw -> {

                if (itemRaw.getId() == supplierOrderItemForm.getItemRawId()) {

                    SupplierOrderItem supplierOrderItem = new SupplierOrderItem();
                    supplierOrderItem.setItemRaw(itemRaw);
                    supplierOrderItem.setQuantityOrdered(supplierOrderItemForm.getQuantityOrdered());
                    supplierOrderItem.setQuantityReceived(0);
                    supplierOrderItems.add(supplierOrderItem);
                }
            });
        });

        supplierOrder = new SupplierOrder();
        supplierOrder.setSupplierOrderItems(supplierOrderItems);
        supplierOrder.setCreatedDate(getDate());
        supplierOrder.setStatus(supplierOrderForm.getStatus());
        supplierOrder.setSupplier(supplier);

        return supplierOrderRepo.save(supplierOrder);
    }

    private SupplierOrder update(SupplierOrderForm supplierOrderForm, Supplier supplier) {

        supplierOrder = supplierOrderRepo
                .findById(supplierOrderForm.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier Order not found for this id :: " + supplierOrderForm.getId()));

        Set<SupplierOrderItem> supplierOrderItems = new HashSet<>();

        supplierOrderForm.getSupplierOrderItems().forEach(supplierOrderItemForm -> {

            supplier.getItemsRaw().forEach(itemRaw -> {

                if (itemRaw.getId() == supplierOrderItemForm.getItemRawId()) {

                    SupplierOrderItem supplierOrderItem = new SupplierOrderItem();
                    supplierOrderItem.setItemRaw(itemRaw);
                    supplierOrderItem.setQuantityOrdered(supplierOrderItemForm.getQuantityOrdered());
                    supplierOrderItem.setQuantityReceived(0);
                    supplierOrderItems.add(supplierOrderItem);
                }
            });
        });


        supplierOrderItemRepo.deleteAllBySupplierOrderId(supplierOrderForm.getId());

        supplierOrder.getSupplierOrderItems().clear();
        supplierOrder.setSupplierOrderItems(supplierOrderItems);

        return supplierOrderRepo.save(supplierOrder);
    }

    public ResponseEntity<?> deleteOrder(int id) {

        supplierOrderRepo
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier Order not found for this id :: " + id));

        supplierOrderRepo.deleteById(id);

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    public void createOrderByMonitor(List<ItemRaw> itemRawList) {

        List<ItemRaw> itemRawListValidated = new ArrayList<>();
        List<SupplierOrder> supplierOrderList = new ArrayList<>();
        Set<Supplier> supplierList = new HashSet<>();

        /* Check if Supplier Orders already exist */

        itemRawList.forEach(itemRaw -> {
            Optional<SupplierOrderItem> supplierOrderItem = supplierOrderItemRepo
                    .findByItemRawIdAndSupplierOrderStatus(itemRaw.getId(), "SENT");

            if (!supplierOrderItem.isPresent()) {
                itemRawListValidated.add(itemRaw);
                supplierList.add(itemRaw.getSupplier());
            }
        });


        /* If supplier does not exist, create new orders */

        supplierList.forEach(supplier -> {

            Set<SupplierOrderItem> supplierOrderItems = new HashSet<>();

            itemRawListValidated.forEach(itemRaw -> {
                if (itemRaw.getSupplier().getId() == supplier.getId()) {
                    SupplierOrderItem supplierOrderItem = new SupplierOrderItem();
                    supplierOrderItem.setItemRaw(itemRaw);
                    supplierOrderItem.setQuantityOrdered(itemRaw.getItemRawReorder().getQuantity());
                    supplierOrderItem.setQuantityReceived(0);

                    supplierOrderItems.add(supplierOrderItem);
                }
            });

            supplierOrder = new SupplierOrder();
            supplierOrder.setSupplierOrderItems(supplierOrderItems);
            supplierOrder.setCreatedDate(getDate());
            supplierOrder.setStatus("SENT");
            supplierOrder.setSupplier(supplier);

            supplierOrderList.add(supplierOrder);
        });

        supplierOrderRepo.saveAll(supplierOrderList);
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



