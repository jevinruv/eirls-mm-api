package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class CompleteItemReorder {

    @Id
    private int id;
    private int quantity;
    private int level;

    @OneToOne
    @JoinColumn
    @MapsId
    @JsonIgnoreProperties("completeItemReorder")
    private CompleteItem completeItem;

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

    public CompleteItem getCompleteItem() {
        return completeItem;
    }

    public void setCompleteItem(CompleteItem completeItem) {
        this.completeItem = completeItem;
    }
}
