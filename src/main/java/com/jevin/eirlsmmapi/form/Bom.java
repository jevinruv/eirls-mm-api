package com.jevin.eirlsmmapi.form;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(using = BomDeserializer.class)
public class Bom {

    private int id;
    private String bomId;
    private int status;

    private List<BomItem> bomItemList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBomId() {
        return bomId;
    }

    public void setBomId(String bomId) {
        this.bomId = bomId;
    }

    public List<BomItem> getBomItemList() {
        return bomItemList;
    }

    public void setBomItemList(List<BomItem> bomItemList) {
        this.bomItemList = bomItemList;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
