package com.jevin.eirlsmmapi.form;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = BomItemDeserializer.class)
public class BomItem {

    private int quantity;
    private int itemRawId;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemRawId() {
        return itemRawId;
    }

    public void setItemRawId(int itemRawId) {
        this.itemRawId = itemRawId;
    }
}
