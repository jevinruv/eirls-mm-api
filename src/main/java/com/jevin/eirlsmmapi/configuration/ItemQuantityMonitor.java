package com.jevin.eirlsmmapi.configuration;

import com.jevin.eirlsmmapi.model.ItemRaw;
import com.jevin.eirlsmmapi.service.ItemCompleteService;
import com.jevin.eirlsmmapi.service.ItemRawService;
import com.jevin.eirlsmmapi.service.SupplierOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemQuantityMonitor {

    @Autowired
    ItemRawService itemRawService;

    @Autowired
    ItemCompleteService itemCompleteService;

    @Autowired
    SupplierOrderService supplierOrderService;


    @Scheduled(fixedDelay = 5000)
    public void checkItemRawQuantities() {

        List<ItemRaw> itemRawList = itemRawService.getItemRawLowQuantities();

        if (!itemRawList.isEmpty()) {
            supplierOrderService.createOrderByMonitor(itemRawList);
        }
    }

    @Scheduled(fixedDelay = 5000)
    public void checkItemCompleteQuantities() {
        itemCompleteService.getItemCompleteLowQuantities();
    }

}
