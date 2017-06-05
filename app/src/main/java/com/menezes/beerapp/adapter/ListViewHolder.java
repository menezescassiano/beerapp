package com.menezes.beerapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.menezes.beerapp.R;
import com.menezes.beerapp.model.BeerData;
import com.menezes.beerapp.model.BeerObject;
import com.menezes.beerapp.util.BitmapTransform;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by cassiano.menezes on 30/05/2017.
 */

public class ListViewHolder extends RecyclerView.ViewHolder {

    private WeakReference<OnInteractionListener> listener;

    public ListViewHolder(View itemView, final WeakReference<OnInteractionListener> listener) {
        super(itemView);
        this.listener = listener;
        itemView.setOnClickListener(itemOnClickListener);
    }

    private View.OnClickListener itemOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.get().onBeerSelected(getAdapterPosition());
        }
    };

    public void populateList(List<BeerObject> beerList) {
        String beerName;
        TextView tv = (TextView) itemView.findViewById(R.id.beer_name);
        beerName = beerList.get(getAdapterPosition()).getName();
        tv.setText(beerName);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.thumbnail);

        if (!beerList.get(getAdapterPosition()).getThumbnail().isEmpty()) {
            Picasso.with(imageView.getContext())
                    .load(beerList.get(getAdapterPosition()).getThumbnail())
                    .transform(new BitmapTransform(50, 50))
                    .skipMemoryCache()
                    .resize(50, 50)
                    .centerInside()
                    .into(imageView);
        }

    }

    public interface OnInteractionListener {
        void onBeerSelected(int position);
    }

}
