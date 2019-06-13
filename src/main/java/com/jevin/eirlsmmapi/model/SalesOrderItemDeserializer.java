package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.jevin.eirlsmmapi.repository.ItemCompleteRepo;
import com.jevin.eirlsmmapi.repository.ItemRawRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class SalesOrderItemDeserializer extends JsonDeserializer<SalesOrderItem> {


    private static ItemRawRepo itemRawRepo;

    private static ItemCompleteRepo itemCompleteRepo;

    private SalesOrderItem salesOrderItem;


    public SalesOrderItemDeserializer() {
    }

    @Autowired
    public SalesOrderItemDeserializer(ItemRawRepo itemRawRepo, ItemCompleteRepo itemCompleteRepo) {
        this.itemRawRepo = itemRawRepo;
        this.itemCompleteRepo = itemCompleteRepo;
    }

    @Override
    public SalesOrderItem deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        JsonNode node = p.getCodec().readTree(p);

        int itemId = node.get("material_order_id").intValue();
        int quantity = node.get("product_quantity").intValue();
        String type = node.get("product_type").textValue();

        getItem(type, itemId);

        salesOrderItem.setQuantity(quantity);

        return salesOrderItem;
    }

    public void getItem(String type, int id) {

        if (type.isEmpty() && id == 0) {
            return;
        }

        salesOrderItem = new SalesOrderItem();

        if (type.equals("Raw Material")) {

            Optional<ItemRaw> itemRawOptional = itemRawRepo.findById(id);

            if (itemRawOptional.isPresent()) {
                ItemRaw itemRaw = itemRawOptional.get();
                salesOrderItem.setItemRaw(itemRaw);
            }

        } else {

            Optional<ItemComplete> itemCompleteOptional = itemCompleteRepo.findById(id);

            if (itemCompleteOptional.isPresent()) {
                ItemComplete itemComplete = itemCompleteOptional.get();
                salesOrderItem.setItemComplete(itemComplete);
            }
        }
    }
}
