package net.gideonbros.dailymeal.presentation.view.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.google.android.gms.common.api.GoogleApiClient;
import io.realm.RealmResults;
import java.util.Locale;
import javax.inject.Inject;
import net.gideonbros.dailymeal.DailyMealApplication;
import net.gideonbros.dailymeal.R;
import net.gideonbros.dailymeal.data.models.DailyMealModel;
import net.gideonbros.dailymeal.presentation.presenter.IDailyMealPresenter;
import net.gideonbros.dailymeal.presentation.view.IDailyMealView;
import net.gideonbros.dailymeal.presentation.view.adapter.DailyMealRecyclerAdapter;

public class DailyMealActivity extends NetworkActivity
    implements IDailyMealView, GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, SearchView.OnQueryTextListener,
    DailyMealRecyclerAdapter.OnClickListener {

  @Inject IDailyMealPresenter presenter;

  DailyMealRecyclerAdapter adapter;
  SearchView searchView;

  @Override int getLayoutId() {
    return R.layout.activity_daily_meal;
  }

  @Override int getMenuLayoutId() {
    return R.menu.daily_meal;
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((DailyMealApplication) getApplication()).getComponent().inject(DailyMealActivity.this);

    showWelcomeMessage();

    initRecyclerView();
    presenter.setView(this);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    presenter.removeView(this);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);

    final MenuItem searchItem = menu.findItem(R.id.action_search);
    searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
    searchView.setOnQueryTextListener(this);

    return true;
  }

  @Override public boolean onQueryTextChange(String searchString) {
    presenter.filterData(searchString);
    return true;
  }

  @Override public boolean onQueryTextSubmit(String query) {
    return false;
  }

  @Override void onLocationFound() {
    Snackbar.make(relativeLayout,
        String.format(getResources().getString(R.string.location_detected),
            lastLocation.getLatitude(), lastLocation.getLongitude()), Snackbar.LENGTH_LONG).show();
    if (checkNetworkConnection()) {
      presenter.startCollectingData(lastLocation.getLatitude(), lastLocation.getLatitude());
    }
  }

  @Override void onNetworkConnected() {
    if (lastLocation != null) {
      presenter.startCollectingData(lastLocation.getLatitude(), lastLocation.getLatitude());
    }
  }

  @Override public void showWelcomeMessage() {
    Snackbar snackbar =
        Snackbar.make(relativeLayout, getString(R.string.welcome), Snackbar.LENGTH_SHORT);
    snackbar.show();
  }

  @Override public void initDailyMeals(@NonNull RealmResults<DailyMealModel> dailyMealModels) {
    progressBar.setVisibility(View.GONE);
    adapter.setDailyMeals(dailyMealModels);
    adapter.addListener();
  }

  @Override public void showDailyMeals(@NonNull RealmResults<DailyMealModel> dailyMealModels) {
    adapter.setDailyMeals(dailyMealModels);
  }

  @Override public void showProgressBar() {
    if (adapter.getItemCount() == 0) progressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgressBar() {
    progressBar.setVisibility(View.GONE);
  }

  @Override public void onOrderClick(int restaurantId) {
    Intent myIntent = new Intent(this, RestaurantActivity.class);
    myIntent.putExtra(RestaurantActivity.RESTAURANT_ID, restaurantId);
    startActivity(myIntent);
  }

  @Override public void onDirectionsClick(String restaurantAddress) {
    String uri = String.format(Locale.ENGLISH, "google.navigation:q=%s", restaurantAddress);
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
    intent.setPackage("com.google.android.apps.maps");
    startActivity(intent);
  }

  protected void initRecyclerView() {
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(linearLayoutManager);
    adapter = new DailyMealRecyclerAdapter(this, this);
    recyclerView.setAdapter(adapter);
  }
}
