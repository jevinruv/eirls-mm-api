package com.jevin.eirlsmmapi.form;

import java.util.List;

public class SupplierOrderReceivedForm {

    private int supplierOrderId;
    private List<SupplierOrderItemForm> supplierOrderItems;

    public int getSupplierOrderId() {
        return supplierOrderId;
    }

    public void setSupplierOrderId(int supplierOrderId) {
        this.supplierOrderId = supplierOrderId;
    }

    public List<SupplierOrderItemForm> getSupplierOrderItems() {
        return supplierOrderItems;
    }

    public void setSupplierOrderItems(List<SupplierOrderItemForm> supplierOrderItems) {
        this.supplierOrderItems = supplierOrderItems;
    }
}
