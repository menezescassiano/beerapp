package com.menezes.beerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.menezes.beerapp.R;
import com.menezes.beerapp.model.BeerData;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cassiano.menezes on 29/05/2017.
 */

public class ListAdapter extends RecyclerView.Adapter {

    private ArrayList<String> beersNames;
    private List<BeerData> beerList;
    final WeakReference<ListViewHolder.OnInteractionListener> listener;

    public ListAdapter(List<BeerData> beerList, ListViewHolder.OnInteractionListener listener) {
        this.beerList = beerList;
        beersNames = getBeersNames(beerList);
        this.listener = new WeakReference<>(listener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.beer_list_item, parent, false);
        return new ListViewHolder(view, context, beerList, listener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListViewHolder listViewHolder = (ListViewHolder) holder;
        listViewHolder.populateList(beersNames.get(position));
    }

    @Override
    public int getItemCount() {
        return beersNames.size();
    }

    private ArrayList<String> getBeersNames(List<BeerData> beerList) {
        ArrayList<String> beersNames = new ArrayList();
        for (BeerData beerData : beerList) {
            if(beerData.getStyle() != null) {
                beersNames.add(beerData.getStyle().getName());
            } else {
                beersNames.add(beerData.getName());
            }

        }

        return beersNames;
    }
}
