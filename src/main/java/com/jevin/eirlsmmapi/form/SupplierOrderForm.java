package com.jevin.eirlsmmapi.form;

public class SupplierOrderForm {

    private int SupplierOrderId;
    private int itemRawId;
    private int quantity;

    public int getSupplierOrderId() {
        return SupplierOrderId;
    }

    public void setSupplierOrderId(int supplierOrderId) {
        SupplierOrderId = supplierOrderId;
    }

    public int getItemRawId() {
        return itemRawId;
    }

    public void setItemRawId(int itemRawId) {
        this.itemRawId = itemRawId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
