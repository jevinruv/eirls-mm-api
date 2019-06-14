package com.jevin.eirlsmmapi.form;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class BomItemDeserializer extends JsonDeserializer<BomItem> {

    @Override
    public BomItem deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        JsonNode node = p.getCodec().readTree(p);

        int quantity = node.get("quantity").intValue();

        JsonParser materialParser = node.findValue("material").traverse();

        JsonNode materialNode = p.getCodec().readTree(materialParser);
        int itemRawId = materialNode.get("material_ref_id").intValue();


        BomItem bomItem = new BomItem();
        bomItem.setItemRawId(itemRawId);
        bomItem.setQuantity(quantity);

        return bomItem;
    }
}
