package com.menezes.beerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.menezes.beerapp.R;

import java.util.ArrayList;

/**
 * Created by cassiano.menezes on 29/05/2017.
 */

public class ListAdapter extends RecyclerView.Adapter {

    private ArrayList<String> beersNames;
    private Context context;

    public ListAdapter(ArrayList<String> beersNames) {
        this.beersNames = beersNames;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.beer_list_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListViewHolder contractViewHolder = (ListViewHolder) holder;
        contractViewHolder.populateList(beersNames.get(position));
    }

    @Override
    public int getItemCount() {
        return beersNames.size();
    }
}
