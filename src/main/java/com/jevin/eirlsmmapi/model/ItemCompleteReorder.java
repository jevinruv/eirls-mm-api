package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class ItemCompleteReorder {

    @Id
    private int id;
    private int quantity;
    private int level;

    @OneToOne
    @JoinColumn
    @MapsId
    @JsonIgnoreProperties("itemCompleteReorder")
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ItemComplete getItemComplete() {
        return itemComplete;
    }

    public void setItemComplete(ItemComplete itemComplete) {
        this.itemComplete = itemComplete;
    }
}
