package com.jevin.eirlsmmapi.service;

import com.jevin.eirlsmmapi.model.SalesOrder;
import com.jevin.eirlsmmapi.repository.SalesOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SalesOrderService {

    @Autowired
    SalesOrderRepo salesOrderRepo;

//    final String salesOrderUrl = "http://eirls.herokuapp.com/materialEnquiry/";
    final String salesOrderUrl = "https://api.myjson.com/bins/gtkx4";

    public List<SalesOrder> getSalesOrders() {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<SalesOrder[]> salesOrderResponseEntity = restTemplate.getForEntity(salesOrderUrl, SalesOrder[].class);

        if (!salesOrderResponseEntity.getStatusCode().is2xxSuccessful()) {

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
            }
        });

    }
}
