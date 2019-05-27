package com.jevin.eirlsmmapi.configuration;

import com.jevin.eirlsmmapi.model.SalesOrder;
import com.jevin.eirlsmmapi.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SalesOrderMonitor {

    @Autowired
    SalesOrderService salesOrderService;

    @Scheduled(fixedDelay = 5000)
    public void checkNewSalesOrders() {

        List<SalesOrder> salesOrderList = salesOrderService.getSalesOrders();

        if (!salesOrderList.isEmpty()) {
            salesOrderService.createSalesOrder(salesOrderList);
        }
    }
}
