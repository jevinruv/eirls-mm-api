package com.jevin.eirlsmmapi.configuration;

import com.jevin.eirlsmmapi.model.CompleteItem;
import com.jevin.eirlsmmapi.model.ItemRaw;
import com.jevin.eirlsmmapi.repository.CompleteItemRepo;
import com.jevin.eirlsmmapi.repository.ItemRawRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemQuantityMonitor {

    @Autowired
    ItemRawRepo itemRawRepo;

    @Autowired
    CompleteItemRepo completeItemRepo;

    @Scheduled(fixedRate = 5000)
    public void checkRawItemQuantities() {

//        System.out.println("Im running");
        List<ItemRaw> itemRawReorderList = new ArrayList<>();

        List<ItemRaw> itemRawList = itemRawRepo.findAll();

        itemRawList.forEach(rawItem -> {

            if (rawItem.getQuantity() <= rawItem.getItemRawReorder().getLevel()) {
                itemRawReorderList.add(rawItem);
            }
        });

        sendSupplierOrder(itemRawReorderList);
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

    public void sendSupplierOrder(List<ItemRaw> itemRawReorderList) {
        System.out.println(itemRawReorderList.toString());
    }
}
