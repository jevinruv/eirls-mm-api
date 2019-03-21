package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@Entity
public class SupplierContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String phone;
    private String email;
    private String location_main;
    private String location_delivery;
    private String location_collection;

    @OneToOne
    @JoinColumn
    @MapsId
    @JsonIgnoreProperties("supplierContact")
    private Supplier supplier;

    public SupplierContact() {
    }

    public SupplierContact(String phone, String location_main, String location_delivery, String location_collection, String email, Supplier supplier) {
        this.phone = phone;
        this.location_main = location_main;
        this.location_delivery = location_delivery;
        this.location_collection = location_collection;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation_main() {
        return location_main;
    }

    public void setLocation_main(String location_main) {
        this.location_main = location_main;
    }

    public String getLocation_delivery() {
        return location_delivery;
    }

    public void setLocation_delivery(String location_delivery) {
        this.location_delivery = location_delivery;
    }

    public String getLocation_collection() {
        return location_collection;
    }

    public void setLocation_collection(String location_collection) {
        this.location_collection = location_collection;
    }
}
