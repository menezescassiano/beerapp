package com.menezes.beerapp.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.menezes.beerapp.R;
import com.menezes.beerapp.adapter.ListViewHolder;
import com.menezes.beerapp.database.BeersDataSource;
import com.menezes.beerapp.fragment.BeerDetailsFragment;
import com.menezes.beerapp.fragment.HistoryFragment;
import com.menezes.beerapp.model.BeerObject;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity implements ListViewHolder.OnInteractionListener {

    private static final String BEER_OBJECTS = "BEER_OBJECTS";
    private static final String SELECTED_BEER_PICTURE = "SELECTED_BEER_PICTURE";
    private static final String SELECTED_BEER_DESCRIPTION = "SELECTED_BEER_DESCRIPTION";
    private static final String SELECTED_BEER_NAME = "SELECTED_BEER_NAME";
    private static final String SELECTED_BEER_THUMBNAIL = "SELECTED_BEER_THUMBNAIL";
    private BeersDataSource beersDataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setTitle(R.string.history);
        beersDataSource = new BeersDataSource(this);
        callHistoryFragment(beersDataSource.getFavedBeers());
    }


    private void callHistoryFragment(ArrayList<BeerObject> beerObjects) {
        Fragment fragment = new HistoryFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(BEER_OBJECTS, beerObjects);
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName()).commit();
    }

    @Override
    public void onBeerSelected(int position) {
        Fragment fragment = new BeerDetailsFragment();
        BeerObject beerObject = beersDataSource.getFavedBeers().get(position);
        Bundle bundle = new Bundle();
        bundle.putString(SELECTED_BEER_PICTURE, beerObject.getLabelUrl());
        bundle.putString(SELECTED_BEER_DESCRIPTION, beerObject.getDescription());
        bundle.putString(SELECTED_BEER_NAME, beerObject.getName());
        bundle.putString(SELECTED_BEER_THUMBNAIL, beerObject.getThumbnail());
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }
}
