package com.menezes.beerapp.model;

/**
 * Created by cassiano.menezes on 04/06/2017.
 */

public class BeerObject {

    private long id;
    private String name;
    private String description;
    private String labelUrl;

    public BeerObject() {

    }

    public BeerObject(String name, String description, String labelUrl) {
        this.name = name;
        this.description = description;
        this.labelUrl = labelUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLabelUrl() {
        return labelUrl;
    }

    public void setLabelUrl(String labelUrl) {
        this.labelUrl = labelUrl;
    }
}
