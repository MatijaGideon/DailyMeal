package net.gideonbros.dailymeal.presentation.view.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import java.util.Locale;
import javax.inject.Inject;
import net.gideonbros.dailymeal.DailyMealApplication;
import net.gideonbros.dailymeal.R;
import net.gideonbros.dailymeal.data.models.RestaurantModel;
import net.gideonbros.dailymeal.presentation.presenter.IRestaurantPresenter;
import net.gideonbros.dailymeal.presentation.view.IRestaurantView;

/**
 * Created by Matija on 4.3.2017..
 */

public class RestaurantActivity extends AppCompatActivity implements IRestaurantView {

  static final int REQUEST_CALL = 1;

  public static final String RESTAURANT_ID = "RestaurantId";

  @BindView(R.id.detail_content) CoordinatorLayout coordinatorLayout;
  @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.progress_bar) ProgressBar progressBar;

  @BindView(R.id.image) ImageView image;
  @BindView(R.id.phone) TextView phone;
  @BindView(R.id.email) TextView email;
  @BindView(R.id.location) TextView address;
  @BindView(R.id.website) TextView website;

  @Inject IRestaurantPresenter presenter;

  RestaurantModel restaurantModel;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_restaurant);
    ((DailyMealApplication) getApplication()).getComponent().inject(this);

    ButterKnife.bind(this);

    setSupportActionBar(toolbar);
    if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    Intent mIntent = getIntent();
    int intValue = mIntent.getIntExtra(RESTAURANT_ID, -1);

    presenter.setView(this, intValue);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override public void showRestaurantInfo(@NonNull RestaurantModel restaurantModel) {
    this.restaurantModel = restaurantModel;
    Glide.with(this)
        .load(Uri.parse(restaurantModel.getRestaurantImageUrl()))
        .listener(new RequestListener<Uri, GlideDrawable>() {
          @Override public boolean onException(Exception e, Uri model, Target<GlideDrawable> target,
              boolean isFirstResource) {
            progressBar.setVisibility(View.GONE);
            return false;
          }

          @Override public boolean onResourceReady(GlideDrawable resource, Uri model,
              Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            progressBar.setVisibility(View.GONE);
            return false;
          }
        })
        .crossFade()
        .into(image);
    collapsingToolbar.setTitle(restaurantModel.getRestaurantName());
    phone.setText(restaurantModel.getRestaurantNumber());
    email.setText(restaurantModel.getRestaurantEmail());
    address.setText(restaurantModel.getRestaurantAddress());
    website.setText(restaurantModel.getRestaurantWebPage());
  }

  @OnClick(R.id.phoneLayout) public void callRestaurant() {
    Intent intent = new Intent(Intent.ACTION_CALL);
    intent.setData(Uri.parse("tel:" + restaurantModel.getRestaurantNumber().trim()));
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
        != PackageManager.PERMISSION_GRANTED) {
      requestCallPermission();
    } else {
      startActivity(intent);
    }
  }

  @OnClick(R.id.emailLayout) public void sendEmail() {
    Intent i = new Intent(Intent.ACTION_SEND);
    i.setType("message/rfc822");
    i.putExtra(Intent.EXTRA_EMAIL, new String[] { restaurantModel.getRestaurantEmail() });
    i.putExtra(Intent.EXTRA_SUBJECT, "Daily meal reservation");
    i.putExtra(Intent.EXTRA_TEXT, "Please deliver to me daily meal at 12 o'clock");
    startActivity(Intent.createChooser(i, "Send mail..."));
  }

  @OnClick(R.id.locationLayout) public void showLocation() {
    String uri =
        String.format(Locale.ENGLISH, "geo:0,0?q=%s", restaurantModel.getRestaurantAddress());
    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
    startActivity(intent);
  }

  @OnClick(R.id.websiteLayout) public void openWebsite() {
    Intent browserIntent =
        new Intent(Intent.ACTION_VIEW, Uri.parse(restaurantModel.getRestaurantWebPage()));
    startActivity(browserIntent);
  }

  protected void requestCallPermission() {
    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
        android.Manifest.permission.CALL_PHONE)) {

      Snackbar.make(coordinatorLayout, R.string.permission_location_rationale,
          Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
        @Override public void onClick(View view) {
          ActivityCompat.requestPermissions(RestaurantActivity.this,
              new String[] { Manifest.permission.CALL_PHONE }, REQUEST_CALL);
        }
      }).show();
    } else {
      ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CALL_PHONE },
          REQUEST_CALL);
    }
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    if (requestCode == REQUEST_CALL) {
      if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        callRestaurant();
        Snackbar.make(coordinatorLayout, R.string.permission_location_available,
            Snackbar.LENGTH_SHORT).show();
      } else {
        Snackbar.make(coordinatorLayout, R.string.permission_location_not_granted,
            Snackbar.LENGTH_INDEFINITE).show();
      }
    } else {
      super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
  }
}
