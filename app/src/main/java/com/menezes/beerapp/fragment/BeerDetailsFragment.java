package com.menezes.beerapp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.menezes.beerapp.R;
import com.menezes.beerapp.database.BeersDataSource;
import com.menezes.beerapp.model.BeerObject;
import com.menezes.beerapp.util.BitmapTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.view.View.GONE;

/**
 * Created by cassiano.menezes on 29/05/2017.
 */

public class BeerDetailsFragment extends Fragment {

    private static final String SELECTED_BEER_PICTURE = "SELECTED_BEER_PICTURE";
    private static final String SELECTED_BEER_DESCRIPTION = "SELECTED_BEER_DESCRIPTION";
    private static final String SELECTED_BEER_NAME = "SELECTED_BEER_NAME";
    private static final String SELECTED_BEER_THUMBNAIL = "SELECTED_BEER_THUMBNAIL";

    private static final int MAX_WIDTH = 1024;
    private static final int MAX_HEIGHT = 768;

    int size = (int) Math.ceil(Math.sqrt(MAX_WIDTH * MAX_HEIGHT));

    @InjectView(R.id.image)
    ImageView imageView;

    @InjectView(R.id.text_beer_description)
    TextView textView;

    @InjectView(R.id.fav_border_icon)
    ImageView favBorderIcon;

    @InjectView(R.id.fav_icon)
    ImageView favIcon;

    private String beerName;
    private String beerLabel;
    private String beerDescription;
    private String thumbnail;
    private BeersDataSource beersDataSource;
    private ArrayList<BeerObject> favedBeerObjects = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beer_details, container, false);
        ButterKnife.inject(this, view);
        Bundle bundle = this.getArguments();
        beerLabel = bundle.getString(SELECTED_BEER_PICTURE);
        beerDescription = bundle.getString(SELECTED_BEER_DESCRIPTION);
        beerName = bundle.getString(SELECTED_BEER_NAME);
        thumbnail = bundle.getString(SELECTED_BEER_THUMBNAIL);
        getActivity().setTitle(bundle.getString(beerName));
        favBorderIcon.setOnClickListener(favBorderIconListener);
        favIcon.setOnClickListener(favIconListener);
        beersDataSource = new BeersDataSource(getActivity());
        favedBeerObjects = beersDataSource.getFavedBeers();
        setUI();

        return view;
    }

    private void setUI() {
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

        if (!favedBeerObjects.isEmpty()) {
            for (BeerObject beerObject : favedBeerObjects) {
                if (beerObject.getName().equals(beerName)) {
                    favBorderIcon.setVisibility(GONE);
                    favIcon.setVisibility(View.VISIBLE);
                    return;
                } else {
                    favBorderIcon.setVisibility(View.VISIBLE);
                    favIcon.setVisibility(View.GONE);
                }
            }
        }

    }

    private View.OnClickListener favBorderIconListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            favBorderIcon.setVisibility(GONE);
            favIcon.setVisibility(View.VISIBLE);
            saveFavedBeer();
        }
    };

    private void saveFavedBeer() {
        BeerObject beerObject = new BeerObject(beerName, beerDescription, beerLabel, thumbnail);
        beersDataSource.createBeerEntry(beerObject);
        List<BeerObject> beerObjects = beersDataSource.getFavedBeers();

    }

    private View.OnClickListener favIconListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            favBorderIcon.setVisibility(View.VISIBLE);
            favIcon.setVisibility(View.GONE);
            beersDataSource.deleteBeerRow(beerName);
        }
    };
}
