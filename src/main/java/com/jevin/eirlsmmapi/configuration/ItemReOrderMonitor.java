package com.jevin.eirlsmmapi.configuration;

import com.jevin.eirlsmmapi.model.Item;
import com.jevin.eirlsmmapi.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemReOrderMonitor {

    @Autowired
    ItemRepo itemRepo;

    @Scheduled(fixedRate = 5000)
    public void checkItemQuantities() {

//        System.out.println("Im running");
        List<Item> itemReorderList = new ArrayList<>();

        List<Item> itemList = itemRepo.findAll();

        itemList.forEach(item -> {

            if (item.getQuantity() <= item.getItemReorder().getLevel()) {
                itemReorderList.add(item);
            }
        });

        sendSupplierOrder(itemReorderList);
    }

    public void sendSupplierOrder(List<Item> itemReorderList) {
        System.out.println(itemReorderList);
    }
}
