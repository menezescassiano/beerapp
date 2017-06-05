package com.menezes.beerapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cassiano.menezes on 04/06/2017.
 */

public class BeerObject implements Parcelable{

    private long id;
    private String name;
    private String description;
    private String labelUrl;
    private String thumbnail;

    public BeerObject() {

    }

    public BeerObject(String name, String description, String labelUrl, String thumbnail) {
        this.name = name;
        this.description = description;
        this.labelUrl = labelUrl;
        this.thumbnail = thumbnail;
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
