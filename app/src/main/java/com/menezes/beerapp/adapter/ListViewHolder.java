package com.menezes.beerapp.adapter;

import android.app.Fragment;
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
import android.widget.Toast;

import com.menezes.beerapp.R;
import com.menezes.beerapp.fragment.BeerListFragment;
import com.menezes.beerapp.model.BeerData;
import com.menezes.beerapp.model.BeersResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cassiano.menezes on 30/05/2017.
 */

public class ListViewHolder extends RecyclerView.ViewHolder {

    private View view;
    private Context context;
    private List<BeerData> beerList;

    public ListViewHolder(View itemView, final Context context, List<BeerData> beerList) {
        super(itemView);
        this.view = itemView;
        this.context = context;
        this.beerList = beerList;
        itemView.setOnClickListener(itemOnClickListener);
    }

    private View.OnClickListener itemOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            BeerData beerData = beerList.get(getAdapterPosition());
            Toast.makeText(context, String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }
    };

    public void populateList(String beerName) {
        TextView tv = (TextView) itemView.findViewById(R.id.beer_name);
        tv.setText(beerName);
    }

}
