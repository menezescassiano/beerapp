package com.menezes.beerapp.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.menezes.beerapp.R;

/**
 * Created by cassiano.menezes on 30/05/2017.
 */

public class ListViewHolder extends RecyclerView.ViewHolder {

    private View view;

    public ListViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;
    }

    public void populateList(String beerName) {
        TextView tv = (TextView) itemView.findViewById(R.id.beer_name);
        tv.setText(beerName);
    }

}
