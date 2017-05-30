package com.menezes.beerapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cassiano.menezes on 29/05/2017.
 */

public class BeersResponse implements Parcelable{

    @SerializedName("currentPage")
    private Integer currentPage;
    @SerializedName("numberOfPages")
    private Integer numberOfPages;
    @SerializedName("totalResults")
    private Integer totalResults;
    @SerializedName("data")
    private List<BeerData> data = null;
    @SerializedName("status")
    private String status;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<BeerData> getData() {
        return data;
    }

    public void setData(List<BeerData> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    protected BeersResponse(Parcel in) {
        data = in.createTypedArrayList(BeerData.CREATOR);
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(data);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BeersResponse> CREATOR = new Creator<BeersResponse>() {
        @Override
        public BeersResponse createFromParcel(Parcel in) {
            return new BeersResponse(in);
        }

        @Override
        public BeersResponse[] newArray(int size) {
            return new BeersResponse[size];
        }
    };
}
