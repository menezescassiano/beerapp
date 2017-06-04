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
import com.menezes.beerapp.model.BeersResponse;

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

    private ListViewHolder.OnInteractionListener interactionListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beer_list, container, false);
        ButterKnife.inject(this, view);
        Bundle bundle = this.getArguments();
        beersResponse = bundle.getParcelable(BEERS_RESPONSE);
        beerDataList = beersResponse.getData();
        getActivity().setTitle(R.string.app_name);
        createList(beersResponse != null ? beersResponse.getData() : null);
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

    private void createList(List<BeerData> beerList) {
        adapter = new ListAdapter(beerList, interactionListener);
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
