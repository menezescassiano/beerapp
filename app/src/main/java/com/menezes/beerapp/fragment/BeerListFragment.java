package com.menezes.beerapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.menezes.beerapp.R;
import com.menezes.beerapp.adapter.ListAdapter;
import com.menezes.beerapp.model.BeerData;
import com.menezes.beerapp.model.BeersResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cassiano.menezes on 29/05/2017.
 */

public class BeerListFragment extends Fragment {

    private static String BEERS_RESPONSE = "BEERS_RESPONSE";

    @InjectView(R.id.beers_list)
    RecyclerView rvBeerList;

    private List<BeerData> beerDataList;
    private BeersResponse beersResponse;

    private ListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beer_list, container, false);
        ButterKnife.inject(this, view);
        Bundle bundle = this.getArguments();
        beersResponse = bundle.getParcelable(BEERS_RESPONSE) ;
        beerDataList = beersResponse.getData();
        createList(beersResponse != null ? beersResponse.getData() : null);
        return view;
    }

    private void createList(List<BeerData> beerList) {
        adapter = new ListAdapter(beerList);
        rvBeerList.setAdapter(adapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }
        };
        rvBeerList.setLayoutManager(layout);
    }

    private ArrayList<String> getBeersNames(List<BeerData> beerList) {
        ArrayList<String> beersNames = new ArrayList();
        for (BeerData beerData : beerList) {
            beersNames.add(beerData.getName());
        }

        return beersNames;
    }

    public List<BeerData> getBeerDataList() {
        return beerDataList;
    }
}
