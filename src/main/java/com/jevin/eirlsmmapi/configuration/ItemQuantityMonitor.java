package com.jevin.eirlsmmapi.configuration;

import com.jevin.eirlsmmapi.model.CompleteItem;
import com.jevin.eirlsmmapi.model.RawItem;
import com.jevin.eirlsmmapi.repository.CompleteItemRepo;
import com.jevin.eirlsmmapi.repository.RawItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemQuantityMonitor {

    @Autowired
    RawItemRepo rawItemRepo;

    @Autowired
    CompleteItemRepo completeItemRepo;

    @Scheduled(fixedRate = 5000)
    public void checkRawItemQuantities() {

//        System.out.println("Im running");
        List<RawItem> rawItemReorderList = new ArrayList<>();

        List<RawItem> rawItemList = rawItemRepo.findAll();

        rawItemList.forEach(rawItem -> {

            if (rawItem.getQuantity() <= rawItem.getRawItemReorder().getLevel()) {
                rawItemReorderList.add(rawItem);
            }
        });

        sendSupplierOrder(rawItemReorderList);
    }

    @Scheduled(fixedRate = 5000)
    public void checkCompleteItemQuantities() {

//        System.out.println("Im running");
        List<CompleteItem> completeItemReorderList = new ArrayList<>();

        List<CompleteItem> completeItemList = completeItemRepo.findAll();

        completeItemList.forEach(completeItem -> {

            if (completeItem.getQuantity() <= completeItem.getCompleteItemReorder().getLevel()) {
                completeItemReorderList.add(completeItem);
            }
        });

        System.out.println(completeItemReorderList.toString());
    }

    public void sendSupplierOrder(List<RawItem> rawItemReorderList) {
        System.out.println(rawItemReorderList.toString());
    }
}
