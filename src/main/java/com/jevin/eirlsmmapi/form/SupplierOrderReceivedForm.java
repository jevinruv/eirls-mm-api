package com.jevin.eirlsmmapi.form;

import java.util.List;

public class SupplierOrderReceivedForm {

    private int supplierOrderId;
    private List<SupplierOrderItemReceivedForm> supplierOrderItems;

    public int getSupplierOrderId() {
        return supplierOrderId;
    }

    public void setSupplierOrderId(int supplierOrderId) {
        this.supplierOrderId = supplierOrderId;
    }

    public List<SupplierOrderItemReceivedForm> getSupplierOrderItems() {
        return supplierOrderItems;
    }

    public void setSupplierOrderItems(List<SupplierOrderItemReceivedForm> supplierOrderItems) {
        this.supplierOrderItems = supplierOrderItems;
    }
}
