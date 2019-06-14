package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.jevin.eirlsmmapi.repository.ItemRawRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;

public class BomItemDeserializer extends JsonDeserializer<BomItem> {

    private static ItemRawRepo itemRawRepo;

    public BomItemDeserializer() {
    }

    @Autowired
    public BomItemDeserializer(ItemRawRepo itemRawRepo) {
        this.itemRawRepo = itemRawRepo;
    }

    @Override
    public BomItem deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        JsonNode node = p.getCodec().readTree(p);

        int quantity = node.get("quantity").intValue();

        JsonParser materialParser = node.findValue("material").traverse();

        JsonNode materialNode = p.getCodec().readTree(materialParser);
        int itemRawId = materialNode.get("material_ref_id").intValue();

        Optional<ItemRaw> itemRawOptional = itemRawRepo.findById(itemRawId);
        BomItem bomItem = new BomItem();

        if (itemRawOptional.isPresent()) {
            ItemRaw itemRaw = itemRawOptional.get();
            bomItem.setItemRaw(itemRaw);
            bomItem.setQuantity(quantity);
        }

        return bomItem;
    }
}
