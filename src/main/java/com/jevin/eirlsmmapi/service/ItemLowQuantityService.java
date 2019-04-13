package com.jevin.eirlsmmapi.service;

import com.jevin.eirlsmmapi.model.ItemComplete;
import com.jevin.eirlsmmapi.model.ItemCompleteLowQuantity;
import com.jevin.eirlsmmapi.model.ItemRaw;
import com.jevin.eirlsmmapi.model.ItemRawLowQuantity;
import com.jevin.eirlsmmapi.repository.ItemCompleteLowQuantityRepo;
import com.jevin.eirlsmmapi.repository.ItemCompleteRepo;
import com.jevin.eirlsmmapi.repository.ItemRawLowQuantityRepo;
import com.jevin.eirlsmmapi.repository.ItemRawRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemLowQuantityService {

    @Autowired
    ItemRawRepo itemRawRepo;

    @Autowired
    ItemRawLowQuantityRepo itemRawLowQuantityRepo;

    @Autowired
    ItemCompleteRepo itemCompleteRepo;

    @Autowired
    ItemCompleteLowQuantityRepo itemCompleteLowQuantityRepo;


    public void setItemRawLowQuantities(List<ItemRaw> itemRawReorderList) {

        itemRawLowQuantityRepo.deleteAll();

        itemRawReorderList.forEach(itemRaw -> {

            ItemRawLowQuantity itemRawLowQuantity = new ItemRawLowQuantity();
            itemRawLowQuantity.setItemRaw(itemRaw);

            itemRawLowQuantityRepo.save(itemRawLowQuantity);
        });

    }

    public void setItemCompleteLowQuantities(List<ItemComplete> itemCompleteReorderList) {

        itemCompleteLowQuantityRepo.deleteAll();

        itemCompleteReorderList.forEach(itemComplete -> {

            ItemCompleteLowQuantity itemCompleteLowQuantity = new ItemCompleteLowQuantity();
            itemCompleteLowQuantity.setItemComplete(itemComplete);

            itemCompleteLowQuantityRepo.save(itemCompleteLowQuantity);
        });

    }
}
