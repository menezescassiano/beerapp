package com.menezes.beerapp.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.menezes.beerapp.R;
import com.menezes.beerapp.adapter.ListAdapter;
import com.menezes.beerapp.adapter.ListViewHolder;
import com.menezes.beerapp.model.BeerData;
import com.menezes.beerapp.model.BeerObject;
import com.menezes.beerapp.model.BeersResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class HistoryFragment extends Fragment {
    private static final String BEER_OBJECTS = "BEER_OBJECTS";

    @InjectView(R.id.beers_list)
    RecyclerView rvBeerList;

    private ListAdapter adapter;
    private List<BeerObject> beerObjects;

    private ListViewHolder.OnInteractionListener interactionListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beer_list, container, false);
        ButterKnife.inject(this, view);
        Bundle bundle = this.getArguments();
        beerObjects = (List<BeerObject>) bundle.get(BEER_OBJECTS);
        createList();
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            interactionListener = (ListViewHolder.OnInteractionListener) activity;
        } catch (ClassCastException e) {
            Log.e("ContractFragment::", "Activity must implement OnInteractionListener", e);
            throw e;
        }
    }

    private void createList() {
        adapter = new ListAdapter(beerObjects, interactionListener);
        rvBeerList.setAdapter(adapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity()) {
            @Override
            public boolean canScrollVertically() {
                return true;
            }
        };
        rvBeerList.setLayoutManager(layout);
    }

}
