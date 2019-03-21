package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@Entity
public class SupplierContact {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String phone;
    private String address;
    private String email;

    @OneToOne
    @JoinColumn
    @MapsId
    @JsonIgnoreProperties("supplierContact")
    private Supplier supplier;

    public SupplierContact() {
    }

    public SupplierContact(String phone, String address, String email, Supplier supplier) {
        this.phone = phone;
        this.address = address;
        this.email = email;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
