package com.jevin.eirlsmmapi.configuration;

import com.jevin.eirlsmmapi.model.ItemComplete;
import com.jevin.eirlsmmapi.model.ItemRaw;
import com.jevin.eirlsmmapi.repository.ItemCompleteRepo;
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
    ItemCompleteRepo itemCompleteRepo;

    @Scheduled(fixedRate = 5000)
    public void checkItemRawQuantities() {

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
    public void checkItemCompleteQuantities() {

//        System.out.println("Im running");
        List<ItemComplete> itemCompleteReorderList = new ArrayList<>();

        List<ItemComplete> itemCompleteList = itemCompleteRepo.findAll();

        itemCompleteList.forEach(completeItem -> {

            if (completeItem.getQuantity() <= completeItem.getItemCompleteReorder().getLevel()) {
                itemCompleteReorderList.add(completeItem);
            }
        });

//        System.out.println(itemCompleteReorderList.toString());
    }

    public void sendSupplierOrder(List<ItemRaw> itemRawReorderList) {
//        System.out.println(itemRawReorderList.toString());
    }
}
