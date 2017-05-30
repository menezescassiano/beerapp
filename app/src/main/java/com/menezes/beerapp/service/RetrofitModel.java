package com.menezes.beerapp.service;

import com.menezes.beerapp.model.BeersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cassiano.menezes on 29/05/2017.
 */

public interface RetrofitModel {


    @GET("beers/")
    Call<BeersResponse> getBeers(@Query("key") String apiKey, @Query("abv") String number);
}
