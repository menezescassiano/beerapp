package com.menezes.beerapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cassiano.menezes on 02/06/2017.
 */

public class Labels implements Parcelable{

    @SerializedName("icon")
    private String icon;
    @SerializedName("medium")
    private String medium;
    @SerializedName("large")
    private String large;

    protected Labels(Parcel in) {
        icon = in.readString();
        medium = in.readString();
        large = in.readString();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(icon);
        dest.writeString(medium);
        dest.writeString(large);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Labels> CREATOR = new Creator<Labels>() {
        @Override
        public Labels createFromParcel(Parcel in) {
            return new Labels(in);
        }

        @Override
        public Labels[] newArray(int size) {
            return new Labels[size];
        }
    };
}
