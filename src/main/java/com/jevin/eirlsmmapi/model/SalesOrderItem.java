package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;

@Entity
@JsonDeserialize(using = SalesOrderItemDeserializer.class)
public class SalesOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("salesOrderItems")
    private SalesOrder salesOrder;

    @ManyToOne
    @JoinColumn
    private ItemRaw itemRaw;

    @ManyToOne
    @JoinColumn
    private ItemComplete itemComplete;

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

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
    }

    public ItemRaw getItemRaw() {
        return itemRaw;
    }

    public void setItemRaw(ItemRaw itemRaw) {
        this.itemRaw = itemRaw;
    }

    public ItemComplete getItemComplete() {
        return itemComplete;
    }

    public void setItemComplete(ItemComplete itemComplete) {
        this.itemComplete = itemComplete;
    }
}
