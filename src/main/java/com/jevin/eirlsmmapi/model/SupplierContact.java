package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;


@Entity
public class SupplierContact {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String phone;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    private Supplier supplier;

    public SupplierContact() {
    }

    public SupplierContact(String phone, Supplier supplier) {
        this.phone = phone;
        this.supplier = supplier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
