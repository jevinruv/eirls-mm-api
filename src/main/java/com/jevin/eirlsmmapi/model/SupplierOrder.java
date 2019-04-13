package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class SupplierOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;
    private Date createdDate;

    @OneToMany(mappedBy = "supplierOrder", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("supplierOrder")
    private Set<SupplierOrderItem> supplierOrderItems;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<SupplierOrderItem> getSupplierOrderItems() {
        return supplierOrderItems;
    }

    public void setSupplierOrderItems(Set<SupplierOrderItem> supplierOrderItems) {
        this.supplierOrderItems = supplierOrderItems;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
