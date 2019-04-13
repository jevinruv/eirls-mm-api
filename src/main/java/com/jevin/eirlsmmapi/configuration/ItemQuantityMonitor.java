package com.jevin.eirlsmmapi.configuration;

import com.jevin.eirlsmmapi.service.ItemCompleteService;
import com.jevin.eirlsmmapi.service.ItemRawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ItemQuantityMonitor {

    @Autowired
    ItemRawService itemRawService;

    @Autowired
    ItemCompleteService itemCompleteService;


    @Scheduled(fixedRate = 5000)
    public void checkItemRawQuantities() {
        itemRawService.getItemRawLowQuantities();
    }

    @Scheduled(fixedRate = 5000)
    public void checkItemCompleteQuantities() {
        itemCompleteService.getItemCompleteLowQuantities();
    }

}
