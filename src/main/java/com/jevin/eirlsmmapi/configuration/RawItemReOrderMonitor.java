package com.jevin.eirlsmmapi.configuration;

import com.jevin.eirlsmmapi.model.RawItem;
import com.jevin.eirlsmmapi.repository.RawItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RawItemReOrderMonitor {

    @Autowired
    RawItemRepo rawItemRepo;

    @Scheduled(fixedRate = 5000)
    public void checkItemQuantities() {

//        System.out.println("Im running");
        List<RawItem> rawItemReorderList = new ArrayList<>();

        List<RawItem> rawItemList = rawItemRepo.findAll();

        rawItemList.forEach(item -> {

            if (item.getQuantity() <= item.getRawItemReorder().getLevel()) {
                rawItemReorderList.add(item);
            }
        });

        sendSupplierOrder(rawItemReorderList);
    }

    public void sendSupplierOrder(List<RawItem> rawItemReorderList) {
        System.out.println(rawItemReorderList);
    }
}
