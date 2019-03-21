package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Supplier {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String status;
    private int leadTime;

    @OneToOne(mappedBy = "supplier", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("supplier")
    private SupplierContact supplierContact;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("supplier")
    private Set<Item> items;

    public Supplier() {
    }

    public Supplier(String name, String status, int leadTime, SupplierContact supplierContact, Set<Item> items) {
        this.name = name;
        this.status = status;
        this.leadTime = leadTime;
        this.supplierContact = supplierContact;
        this.items = items;
    }

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

    public SupplierContact getSupplierContact() {
        return supplierContact;
    }

    public void setSupplierContact(SupplierContact supplierContact) {
        this.supplierContact = supplierContact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(int leadTime) {
        this.leadTime = leadTime;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}


