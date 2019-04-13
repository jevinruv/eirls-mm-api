package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class ItemRawReorder {

    @Id
    private int id;
    private int quantity;
    private int level;

    @OneToOne
    @JoinColumn
    @MapsId
    @JsonIgnoreProperties("itemRawReorder")
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ItemRaw getItemRaw() {
        return itemRaw;
    }

    public void setItemRaw(ItemRaw itemRaw) {
        this.itemRaw = itemRaw;
    }
}
