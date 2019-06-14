package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;

@Entity
@JsonDeserialize(using = BomItemDeserializer.class)
public class BomItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("bomItems")
    private SalesOrder bom;

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

    public SalesOrder getBom() {
        return bom;
    }

    public void setBom(SalesOrder bom) {
        this.bom = bom;
    }

    public ItemRaw getItemRaw() {
        return itemRaw;
    }

    public void setItemRaw(ItemRaw itemRaw) {
        this.itemRaw = itemRaw;
    }
}
