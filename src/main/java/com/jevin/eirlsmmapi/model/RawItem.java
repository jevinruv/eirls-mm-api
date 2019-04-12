package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class RawItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int quantity;
    private double price;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("rawItems")
    private Supplier supplier;

    @OneToOne(mappedBy = "rawItem", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("rawItem")
    private RawItemReorder rawItemReorder;

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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public RawItemReorder getRawItemReorder() {
        return rawItemReorder;
    }

    public void setRawItemReorder(RawItemReorder rawItemReorder) {
        this.rawItemReorder = rawItemReorder;
    }
}
