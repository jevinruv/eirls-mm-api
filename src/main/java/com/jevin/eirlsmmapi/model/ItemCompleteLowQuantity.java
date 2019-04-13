package com.jevin.eirlsmmapi.model;

import javax.persistence.*;

@Entity
public class ItemCompleteLowQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "item_complete_id")
    private ItemComplete itemComplete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemComplete getItemComplete() {
        return itemComplete;
    }

    public void setItemComplete(ItemComplete itemComplete) {
        this.itemComplete = itemComplete;
    }
}
