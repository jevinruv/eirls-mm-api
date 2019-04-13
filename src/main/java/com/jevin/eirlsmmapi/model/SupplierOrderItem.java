package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class SupplierOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("supplierOrderItems")
    private SupplierOrder supplierOrder;

    @ManyToOne
    @JoinColumn
    private ItemRaw itemRaw;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public SupplierOrder getSupplierOrder() {
        return supplierOrder;
    }

    public void setSupplierOrder(SupplierOrder supplierOrder) {
        this.supplierOrder = supplierOrder;
    }

    public ItemRaw getItemRaw() {
        return itemRaw;
    }

    public void setItemRaw(ItemRaw itemRaw) {
        this.itemRaw = itemRaw;
    }
}
