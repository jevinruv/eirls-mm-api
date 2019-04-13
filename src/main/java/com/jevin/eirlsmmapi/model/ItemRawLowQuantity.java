package com.jevin.eirlsmmapi.model;

import javax.persistence.*;

@Entity
public class ItemRawLowQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "item_raw_id")
    private ItemRaw itemRaw;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ItemRaw getItemRaw() {
        return itemRaw;
    }

    public void setItemRaw(ItemRaw itemRaw) {
        this.itemRaw = itemRaw;
    }
}
