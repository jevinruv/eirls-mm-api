package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class SalesOrderDeserializer extends JsonDeserializer<SalesOrder> {

    @Override
    public SalesOrder deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        JsonNode node = p.getCodec().readTree(p);

        int id = node.get("order_id").intValue();
        String status = node.get("order_status").textValue();
        String dateOld = node.get("date_placed").textValue();

        JsonParser parser = node.findValue("ord").traverse();
        parser.setCodec(p.getCodec());
        Set<SalesOrderItem> salesOrderItems = parser.readValueAs(new TypeReference<Set<SalesOrderItem>>() {
        });

//        Date date = convertDate(dateOld);

        Date date = new Date(dateOld);

        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setSalesOrderId(id);
        salesOrder.setStatus(status);
        salesOrder.setCreatedDate(date);
        salesOrder.setSalesOrderItems(salesOrderItems);

        return salesOrder;
    }

    private Date convertDate(String strDate) {

        DateFormat to = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss"); // wanted format
        DateFormat from = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"); // current format
        Date convertedDate = null;

        try {
            String toDate = to.format(from.parse(strDate));
            convertedDate = to.parse(toDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertedDate;
    }
}
