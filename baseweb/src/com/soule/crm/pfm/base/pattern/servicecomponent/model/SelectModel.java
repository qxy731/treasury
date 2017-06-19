package com.soule.crm.pfm.base.pattern.servicecomponent.model;

public class SelectModel {
    private String id;
    private String name;

    public SelectModel() {
    }

    public SelectModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
