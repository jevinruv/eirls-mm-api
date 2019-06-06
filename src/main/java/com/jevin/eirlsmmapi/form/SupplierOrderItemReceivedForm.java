package com.jevin.eirlsmmapi.form;

public class SupplierOrderItemReceivedForm {

    private int itemRawId;
    private int quantityReceived;

    public int getItemRawId() {
        return itemRawId;
    }

    public void setItemRawId(int itemRawId) {
        this.itemRawId = itemRawId;
    }

    public int getQuantityReceived() {
        return quantityReceived;
    }

    public void setQuantityReceived(int quantityReceived) {
        this.quantityReceived = quantityReceived;
    }
}
