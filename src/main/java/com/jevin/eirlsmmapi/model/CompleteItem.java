package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class CompleteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int quantity;
    private double price;

    @OneToOne(mappedBy = "completeItem", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("completeItem")
    private CompleteItemReorder completeItemReorder;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CompleteItemReorder getCompleteItemReorder() {
        return completeItemReorder;
    }

    public void setCompleteItemReorder(CompleteItemReorder completeItemReorder) {
        this.completeItemReorder = completeItemReorder;
    }
}
