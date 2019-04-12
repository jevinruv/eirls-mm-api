package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class RawItemReorder {

    @Id
    private int id;
    private int quantity;
    private int level;

    @OneToOne
    @JoinColumn
    @MapsId
    @JsonIgnoreProperties("itemReorder")
    private RawItem rawItem;

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

    public RawItem getRawItem() {
        return rawItem;
    }

    public void setRawItem(RawItem rawItem) {
        this.rawItem = rawItem;
    }
}
