package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class ItemRaw extends Item {

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties("itemsRaw")
    private Supplier supplier;

    @OneToOne(mappedBy = "itemRaw", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("itemRaw")
    private ItemRawReorder itemRawReorder;

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public ItemRawReorder getItemRawReorder() {
        return itemRawReorder;
    }

    public void setItemRawReorder(ItemRawReorder itemRawReorder) {
        this.itemRawReorder = itemRawReorder;
    }
}
