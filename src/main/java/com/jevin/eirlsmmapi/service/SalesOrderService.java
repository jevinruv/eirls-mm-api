package com.jevin.eirlsmmapi.service;

import com.jevin.eirlsmmapi.model.ItemComplete;
import com.jevin.eirlsmmapi.model.ItemRaw;
import com.jevin.eirlsmmapi.model.SalesOrder;
import com.jevin.eirlsmmapi.repository.ItemCompleteRepo;
import com.jevin.eirlsmmapi.repository.ItemRawRepo;
import com.jevin.eirlsmmapi.repository.SalesOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SalesOrderService {

    @Autowired
    SalesOrderRepo salesOrderRepo;

    @Autowired
    ItemCompleteRepo itemCompleteRepo;

    @Autowired
    ItemRawRepo itemRawRepo;

    final String salesOrderUrl = "http://eirls.herokuapp.com/materialEnquiry";
//    final String salesOrderUrl = "https://api.myjson.com/bins/ebbjn";

    public List<SalesOrder> getSalesOrders() {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<SalesOrder[]> salesOrderResponseEntity = restTemplate.getForEntity(salesOrderUrl, SalesOrder[].class);

        if (!salesOrderResponseEntity.getStatusCode().is2xxSuccessful()) {
            return null;
        }

        SalesOrder[] salesOrders = salesOrderResponseEntity.getBody();
        List<SalesOrder> salesOrderList = Arrays.asList(salesOrders);

        return salesOrderList;
    }

    public void createSalesOrder(List<SalesOrder> salesOrderList) {

        salesOrderList.forEach(salesOrder -> {

            Optional<SalesOrder> salesOrderOptional = salesOrderRepo.findBySalesOrderId(salesOrder.getSalesOrderId());

            if (!salesOrderOptional.isPresent()) {
                salesOrderRepo.save(salesOrder);
                reduceItems(salesOrder);
            }
        });

    }

    private void reduceItems(SalesOrder salesOrder) {

        salesOrder.getSalesOrderItems().forEach(salesOrderItem -> {

            if (salesOrderItem.getItemComplete() != null) {
                int quantity = salesOrderItem.getQuantity();
                int id = salesOrderItem.getItemComplete().getId();

                ItemComplete itemComplete = itemCompleteRepo.findById(id).get();
                int currentQuantity = itemComplete.getQuantity();
                itemComplete.setQuantity(currentQuantity - quantity);

                itemCompleteRepo.save(itemComplete);

            } else {
                int quantity = salesOrderItem.getQuantity();
                int id = salesOrderItem.getItemRaw().getId();

                ItemRaw itemRaw = itemRawRepo.findById(id).get();
                int currentQuantity = itemRaw.getQuantity();
                itemRaw.setQuantity(currentQuantity - quantity);

                itemRawRepo.save(itemRaw);
            }
        });
    }

    public List<SalesOrder> getPendingSalesOrders() {

        Optional<List<SalesOrder>> optionalSalesOrderList = salesOrderRepo.findAllByStatus("pending");

        List<SalesOrder> salesOrderList = new ArrayList<>();

        if (optionalSalesOrderList.isPresent()) {
            salesOrderList = optionalSalesOrderList.get();
        }

        return salesOrderList;
    }

    public void validateOrderByTime(List<SalesOrder> salesOrderList) {

        salesOrderList.forEach(salesOrder -> {
            Date createdDate = salesOrder.getCreatedDate();
            Date today = getDate();

            Calendar cal = Calendar.getInstance();
            cal.setTime(createdDate);
            cal.add(Calendar.HOUR_OF_DAY, 24); // adds 24 hours
            Date createdDateUpdated = cal.getTime();

            if (createdDateUpdated.compareTo(today) < 0) {
                cancelOrder(salesOrder);
            }
        });

    }

    private void cancelOrder(SalesOrder salesOrder) {

        Optional<SalesOrder> toUpdateOptional = salesOrderRepo.findById(salesOrder.getId());

        if (toUpdateOptional.isPresent()) {
            SalesOrder toUpdate = toUpdateOptional.get();

            toUpdate.setStatus("cancelled");
            salesOrderRepo.save(toUpdate);
            revertItems(salesOrder);
        }

    }

    private void revertItems(SalesOrder salesOrder) {

        salesOrder.getSalesOrderItems().forEach(salesOrderItem -> {

            if (salesOrderItem.getItemComplete() != null) {
                int quantity = salesOrderItem.getQuantity();
                int id = salesOrderItem.getItemComplete().getId();

                ItemComplete itemComplete = itemCompleteRepo.findById(id).get();
                int currentQuantity = itemComplete.getQuantity();
                itemComplete.setQuantity(currentQuantity + quantity);

                itemCompleteRepo.save(itemComplete);

            } else {
                int quantity = salesOrderItem.getQuantity();
                int id = salesOrderItem.getItemRaw().getId();

                ItemRaw itemRaw = itemRawRepo.findById(id).get();
                int currentQuantity = itemRaw.getQuantity();
                itemRaw.setQuantity(currentQuantity + quantity);

                itemRawRepo.save(itemRaw);
            }
        });
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
