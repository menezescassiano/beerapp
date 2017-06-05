package com.menezes.beerapp.activity;

import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.menezes.beerapp.BuildConfig;
import com.menezes.beerapp.R;
import com.menezes.beerapp.adapter.ListViewHolder;
import com.menezes.beerapp.fragment.BeerDetailsFragment;
import com.menezes.beerapp.fragment.BeerListFragment;
import com.menezes.beerapp.model.BeerData;
import com.menezes.beerapp.model.BeersResponse;
import com.menezes.beerapp.service.RetrofitClient;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class BeersActivity extends AppCompatActivity implements ListViewHolder.OnInteractionListener {

    private static final String BEERS_RESPONSE = "BEERS_RESPONSE";
    private static final String SELECTED_BEER = "SELECTED_BEER";
    private static final String SELECTED_BEER_PICTURE = "SELECTED_BEER_PICTURE";
    private static final String SELECTED_BEER_DESCRIPTION = "SELECTED_BEER_DESCRIPTION";
    private static final String SELECTED_BEER_NAME = "SELECTED_BEER_NAME";
    private static final String SELECTED_BEER_THUMBNAIL = "SELECTED_BEER_THUMBNAIL";

    @InjectView(R.id.progress_bar)
    ProgressBar progressBar;

    private List<BeerData> beerDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers);
        ButterKnife.inject(this);
        requestBeers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.history:
                startActivity(new Intent(this, HistoryActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void requestBeers() {
        Call<BeersResponse> beerCall = new RetrofitClient().getModel().getBeers(BuildConfig.SERVER_KEY, "+10");
        beerCall.enqueue(new Callback<BeersResponse>() {
            @Override
            public void onResponse(Call<BeersResponse> call, Response<BeersResponse> response) {
                progressBar.setVisibility(GONE);
                BeersResponse beersResponse = response.body();
                beerDatas = beersResponse.getData();
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

    @Override
    public void onBeerSelected(int position) {
        Fragment fragment = new BeerDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SELECTED_BEER_PICTURE, beerDatas.get(position).getLabels() != null
                ? beerDatas.get(position).getLabels().getLarge() : "");
        bundle.putString(SELECTED_BEER_DESCRIPTION, beerDatas.get(position).getStyle() != null
                ? beerDatas.get(position).getStyle().getDescription() : beerDatas.get(position).getDescription());
        bundle.putString(SELECTED_BEER_NAME, beerDatas.get(position).getStyle() != null
                ? beerDatas.get(position).getStyle().getName() : beerDatas.get(position).getName());
        bundle.putString(SELECTED_BEER_THUMBNAIL, beerDatas.get(position).getLabels() != null
                ? beerDatas.get(position).getLabels().getIcon() : "");
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }
    /*public void replaceFragmentWithAnimation(android.support.v4.app.Fragment fragment, String tag){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_left);
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(tag);
        transaction.commit();
    }*/
}
