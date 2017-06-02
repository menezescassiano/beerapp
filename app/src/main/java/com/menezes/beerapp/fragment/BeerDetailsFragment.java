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
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cassiano.menezes on 29/05/2017.
 */

public class BeerDetailsFragment extends Fragment {

    private static final String SELECTED_BEER = "SELECTED_BEER";

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
        //Picasso.with(getActivity()).load(beerData.getImageURL).centerInside();
        textView.setText(beerData.getDescription());
    }
}
