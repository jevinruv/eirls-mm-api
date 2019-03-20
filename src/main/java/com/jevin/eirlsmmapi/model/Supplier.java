package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Supplier {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<SupplierContact> supplierContacts;

    public Supplier() {
    }

    public Supplier(String name, Set<SupplierContact> supplierContacts) {
        this.name = name;
        this.supplierContacts = supplierContacts;
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

    public Set<SupplierContact> getSupplierContacts() {
        return supplierContacts;
    }

    public void setSupplierContacts(Set<SupplierContact> supplierContacts) {
        this.supplierContacts = supplierContacts;
    }
}


