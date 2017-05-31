package com.menezes.beerapp.activity;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.menezes.beerapp.BuildConfig;
import com.menezes.beerapp.R;
import com.menezes.beerapp.fragment.BeerListFragment;
import com.menezes.beerapp.model.BeersResponse;
import com.menezes.beerapp.service.RetrofitClient;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class BeersActivity extends AppCompatActivity {

    private static final String BEERS_RESPONSE = "BEERS_RESPONSE";

    @InjectView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers);
        ButterKnife.inject(this);
        requestBeers();
    }

    private void requestBeers() {
        Call<BeersResponse> beerCall = new RetrofitClient().getModel().getBeers(BuildConfig.SERVER_KEY, "+10");
        beerCall.enqueue(new Callback<BeersResponse>() {
            @Override
            public void onResponse(Call<BeersResponse> call, Response<BeersResponse> response) {
                progressBar.setVisibility(GONE);
                BeersResponse beersResponse = response.body();
                callBeersListFragment(beersResponse);
            }

            @Override
            public void onFailure(Call<BeersResponse> call, Throwable t) {
                Toast.makeText(BeersActivity.this, getString(R.string.connection_error), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(GONE);
            }
        });
    }

    private void callBeersListFragment(BeersResponse beersResponse) {
        Fragment fragment = new BeerListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(BEERS_RESPONSE, beersResponse);
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName()).commit();
    }
}
