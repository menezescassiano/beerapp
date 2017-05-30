package com.menezes.beerapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cassiano.menezes on 29/05/2017.
 */

public class BeerData implements Parcelable{

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("nameDisplay")
    private String nameDisplay;
    @SerializedName("description")
    private String description;
    @SerializedName("abv")
    private String abv;
    @SerializedName("ibu")
    private String ibu;
    @SerializedName("styleId")
    private Integer styleId;
    @SerializedName("isOrganic")
    private String isOrganic;
    @SerializedName("status")
    private String status;
    @SerializedName("statusDisplay")
    private String statusDisplay;
    @SerializedName("createDate")
    private String createDate;
    @SerializedName("updateDate")
    private String updateDate;
    @SerializedName("style")
    private Style style;

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

    public String getNameDisplay() {
        return nameDisplay;
    }

    public void setNameDisplay(String nameDisplay) {
        this.nameDisplay = nameDisplay;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbv() {
        return abv;
    }

    public void setAbv(String abv) {
        this.abv = abv;
    }

    public String getIbu() {
        return ibu;
    }

    public void setIbu(String ibu) {
        this.ibu = ibu;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public String getIsOrganic() {
        return isOrganic;
    }

    public void setIsOrganic(String isOrganic) {
        this.isOrganic = isOrganic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDisplay() {
        return statusDisplay;
    }

    public void setStatusDisplay(String statusDisplay) {
        this.statusDisplay = statusDisplay;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    protected BeerData(Parcel in) {
        id = in.readString();
        name = in.readString();
        nameDisplay = in.readString();
        description = in.readString();
        abv = in.readString();
        ibu = in.readString();
        isOrganic = in.readString();
        status = in.readString();
        statusDisplay = in.readString();
        createDate = in.readString();
        updateDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(nameDisplay);
        dest.writeString(description);
        dest.writeString(abv);
        dest.writeString(ibu);
        dest.writeString(isOrganic);
        dest.writeString(status);
        dest.writeString(statusDisplay);
        dest.writeString(createDate);
        dest.writeString(updateDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BeerData> CREATOR = new Creator<BeerData>() {
        @Override
        public BeerData createFromParcel(Parcel in) {
            return new BeerData(in);
        }

        @Override
        public BeerData[] newArray(int size) {
            return new BeerData[size];
        }
    };
}
