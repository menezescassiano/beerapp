package com.menezes.beerapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.menezes.beerapp.BuildConfig;
import com.menezes.beerapp.R;
import com.menezes.beerapp.model.BeersResponse;
import com.menezes.beerapp.service.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, BeersActivity.class);
        startActivity(intent);
    }
}
