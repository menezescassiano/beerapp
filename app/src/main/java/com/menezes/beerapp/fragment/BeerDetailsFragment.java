package com.menezes.beerapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.menezes.beerapp.R;
import com.menezes.beerapp.model.BeerData;
import com.menezes.beerapp.util.BitmapTransform;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cassiano.menezes on 29/05/2017.
 */

public class BeerDetailsFragment extends Fragment {

    private static final String SELECTED_BEER = "SELECTED_BEER";

    private static final int MAX_WIDTH = 1024;
    private static final int MAX_HEIGHT = 768;

    int size = (int) Math.ceil(Math.sqrt(MAX_WIDTH * MAX_HEIGHT));

    @InjectView(R.id.image)
    ImageView imageView;

    @InjectView(R.id.text_beer_description)
    TextView textView;

    private BeerData beerData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beer_details, container, false);
        ButterKnife.inject(this, view);
        Bundle bundle = this.getArguments();
        beerData = bundle.getParcelable(SELECTED_BEER);
        setUI();
        return view;
    }

    private void setUI() {
        if (beerData.getLabels() != null) {
            //Picasso.with(getActivity()).load(beerData.getLabels().getLarge()).resize(600, 600).centerInside().into(imageView);
            Picasso.with(imageView.getContext())
                    .load(beerData.getLabels().getLarge())
                    .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                    .skipMemoryCache()
                    .resize(size, size)
                    .centerInside()
                    .into(imageView);
        }
        if (beerData.getStyle().getDescription() != null) {
            textView.setText(beerData.getStyle().getDescription());
        }

    }
}
