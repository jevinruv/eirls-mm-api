package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class ItemComplete extends Item{

    @OneToOne(mappedBy = "itemComplete", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("itemComplete")
    private ItemCompleteReorder itemCompleteReorder;

    public ItemCompleteReorder getItemCompleteReorder() {
        return itemCompleteReorder;
    }

    public void setItemCompleteReorder(ItemCompleteReorder itemCompleteReorder) {
        this.itemCompleteReorder = itemCompleteReorder;
    }
}
