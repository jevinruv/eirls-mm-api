package com.jevin.eirlsmmapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonDeserialize(using = BomDeserializer.class)
public class Bom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String productionBomId;
    private String status;

    @OneToMany(mappedBy = "bom", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("bom")
    private List<BomItem> bomItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductionBomId() {
        return productionBomId;
    }

    public void setProductionBomId(String productionBomId) {
        this.productionBomId = productionBomId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BomItem> getBomItems() {
        return bomItems;
    }

    public void setBomItems(List<BomItem> bomItems) {
        this.bomItems = bomItems;
        this.bomItems.forEach(bomItem -> bomItem.setBom(this));
    }
}
