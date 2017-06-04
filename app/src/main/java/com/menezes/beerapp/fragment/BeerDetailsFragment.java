package com.menezes.beerapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.menezes.beerapp.R;
import com.menezes.beerapp.util.BitmapTransform;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cassiano.menezes on 29/05/2017.
 */

public class BeerDetailsFragment extends Fragment {

    private static final String SELECTED_BEER_PICTURE = "SELECTED_BEER_PICTURE";
    private static final String SELECTED_BEER_DESCRIPTION = "SELECTED_BEER_DESCRIPTION";
    private static final String SELECTED_BEER_NAME = "SELECTED_BEER_NAME";

    private static final int MAX_WIDTH = 1024;
    private static final int MAX_HEIGHT = 768;

    int size = (int) Math.ceil(Math.sqrt(MAX_WIDTH * MAX_HEIGHT));

    @InjectView(R.id.image)
    ImageView imageView;

    @InjectView(R.id.text_beer_description)
    TextView textView;

    //private BeerData beerData;
    private String beerLabel;
    private String beerDescription;
    private String beerName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beer_details, container, false);
        ButterKnife.inject(this, view);
        Bundle bundle = this.getArguments();
        beerLabel = bundle.getString(SELECTED_BEER_PICTURE);
        beerDescription = bundle.getString(SELECTED_BEER_DESCRIPTION);
        getActivity().setTitle(bundle.getString(SELECTED_BEER_NAME));
        setUI();
        return view;
    }

    private void setUI() {
        //Picasso.with(getActivity()).load(beerData.getLabels().getLarge()).resize(600, 600).centerInside().into(imageView);
        if (!beerLabel.isEmpty()) {
            Picasso.with(imageView.getContext())
                    .load(beerLabel)
                    .transform(new BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                    .skipMemoryCache()
                    .resize(size, size)
                    .centerInside()
                    .into(imageView);
        }

        textView.setText(beerDescription);

    }
}
