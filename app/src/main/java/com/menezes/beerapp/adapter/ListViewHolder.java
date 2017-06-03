package com.menezes.beerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.menezes.beerapp.R;
import com.menezes.beerapp.model.BeerData;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by cassiano.menezes on 30/05/2017.
 */

public class ListViewHolder extends RecyclerView.ViewHolder {

    private View view;
    private Context context;
    private List<BeerData> beerList;
    private WeakReference<OnInteractionListener> listener;

    public ListViewHolder(View itemView, final Context context, List<BeerData> beerList,
                          final WeakReference<OnInteractionListener> listener) {
        super(itemView);
        this.view = itemView;
        this.context = context;
        this.beerList = beerList;
        this.listener = listener;
        itemView.setOnClickListener(itemOnClickListener);
    }

    private View.OnClickListener itemOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //BeerData beerData = beerList.get(getAdapterPosition());
            listener.get().onBeerSelected(getAdapterPosition());
            //Toast.makeText(context, String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
        }
    };

    public void populateList(String beerName) {
        TextView tv = (TextView) itemView.findViewById(R.id.beer_name);
        tv.setText(beerName);
    }

    public interface OnInteractionListener {
        void onBeerSelected(int position);
    }

}
