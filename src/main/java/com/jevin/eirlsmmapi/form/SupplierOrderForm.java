package com.jevin.eirlsmmapi.form;

import java.util.List;

public class SupplierOrderForm {

    private int id;
    private int supplierId;
    private String status;
    private List<SupplierOrderItemForm> supplierOrderItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SupplierOrderItemForm> getSupplierOrderItems() {
        return supplierOrderItems;
    }

    public void setSupplierOrderItems(List<SupplierOrderItemForm> supplierOrderItems) {
        this.supplierOrderItems = supplierOrderItems;
    }
}
