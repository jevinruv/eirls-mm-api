package com.jevin.eirlsmmapi.form;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.List;

public class BomDeserializer extends JsonDeserializer<Bom> {

    @Override
    public Bom deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        JsonNode node = p.getCodec().readTree(p);

        int id = node.get("id").intValue();
        int status = node.get("status_id").intValue();

        JsonParser parser = node.findValue("bill_items").traverse();
        parser.setCodec(p.getCodec());
        List<BomItem> bomItems = parser.readValueAs(new TypeReference<List<BomItem>>() {
        });

        Bom bom = new Bom();
        bom.setId(id);
        bom.setStatus(status);
        bom.setBomItemList(bomItems);

        return bom;
    }
}
