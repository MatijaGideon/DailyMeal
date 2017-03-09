package net.gideonbros.dailymeal.presentation.view.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import net.gideonbros.dailymeal.R;

/**
 * Created by Matija on 5.3.2017..
 */

public abstract class LocationActivity extends DrawerActivity
    implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
    LocationListener {

  public static final String BUNDLE = "Refresh";

  abstract void onLocationFound();

  @BindView(R.id.recycler_progress_bar) protected ProgressBar progressBar;
  @BindView(R.id.fab) protected FloatingActionButton fab;
  @BindView(R.id.daily_meal_recycler_view) protected RecyclerView recyclerView;
  @BindView(R.id.content_daily_meal) protected RelativeLayout relativeLayout;

  protected GoogleApiClient googleApiClient;
  private LocationRequest locationRequest;
  protected Location lastLocation;

  // Location updates intervals in sec
  private static int UPDATE_INTERVAL = 300000; // 5 min
  private static int FATEST_INTERVAL = 180000; // 3 min
  private static int DISPLACEMENT = 1000; // 1 km

  static final int REQUEST_LOCATION = 0;
  static String[] PERMISSIONS_LOCATION = {
      android.Manifest.permission.ACCESS_COARSE_LOCATION,
      android.Manifest.permission.ACCESS_FINE_LOCATION
  };

  static final int PLAY_SERVICES_RESOLUTION_REQUEST = 400;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (checkPlayServices()) {
      initGoogleApiClient();
      createLocationRequest();
    }
  }

  protected void onStart() {
    if (googleApiClient != null) {
      googleApiClient.connect();
    }
    super.onStart();
  }

  protected void onStop() {
    if (googleApiClient != null) {
      googleApiClient.disconnect();
    }
    super.onStop();
  }

  @Override protected void onPause() {
    super.onPause();
    stopLocationUpdates();
  }

  @OnClick(R.id.fab) public void refreshLocation() {
    checkPlayServices();
    if (googleApiClient.isConnected()) {
      Bundle bundle = new Bundle();
      bundle.putBoolean(BUNDLE, true);
      onConnected(bundle);
    }
  }

  private boolean checkPlayServices() {
    GoogleApiAvailability googleAPI = GoogleApiAvailability.getInstance();
    int result = googleAPI.isGooglePlayServicesAvailable(this);
    if (result != ConnectionResult.SUCCESS) {
      if (googleAPI.isUserResolvableError(result)) {
        googleAPI.getErrorDialog(this, result, PLAY_SERVICES_RESOLUTION_REQUEST).show();
      }
      return false;
    }
    return true;
  }

  protected synchronized void initGoogleApiClient() {
    if (googleApiClient == null) {
      googleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
          .addOnConnectionFailedListener(this)
          .addApi(LocationServices.API)
          .build();
    }
  }

  protected void createLocationRequest() {
    locationRequest = new LocationRequest();
    locationRequest.setInterval(UPDATE_INTERVAL);
    locationRequest.setFastestInterval(FATEST_INTERVAL);
    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    locationRequest.setSmallestDisplacement(DISPLACEMENT);
  }

  @Override public void onConnected(@Nullable Bundle bundle) {
    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
        != PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(this,
        android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      requestLocationPermission();
    } else {
      LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest,
          this);
      Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
      if (location != null && (lastLocation == null || bundle != null || isLocationChanged(location,
          lastLocation))) {
        lastLocation = location;
        onLocationFound();
      } else if (location == null) {
        Snackbar.make(relativeLayout, R.string.no_location_detected, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.open, new View.OnClickListener() {
              @Override public void onClick(View v) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
              }
            })
            .show();
      }
    }
  }

  private boolean isLocationChanged(Location l1, Location l2) {
    return !(Double.compare(l1.getLongitude(), l2.getLongitude()) == 0
        && Double.compare(l1.getLatitude(), l2.getLatitude()) == 0);
  }

  @Override public void onConnectionSuspended(int i) {
    googleApiClient.connect();
  }

  @Override public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    Snackbar.make(relativeLayout, R.string.location_connection_failed, Snackbar.LENGTH_SHORT)
        .show();
  }

  @Override public void onLocationChanged(Location location) {
    if (location != null && (lastLocation == null || isLocationChanged(location, lastLocation))) {
      this.lastLocation = location;
      onLocationFound();
    }
  }

  protected void stopLocationUpdates() {
    LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
  }

  protected void requestLocationPermission() {
    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
        android.Manifest.permission.ACCESS_FINE_LOCATION)
        || ActivityCompat.shouldShowRequestPermissionRationale(this,
        android.Manifest.permission.ACCESS_COARSE_LOCATION)) {

      Snackbar.make(relativeLayout, R.string.permission_location_rationale,
          Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
        @Override public void onClick(View view) {
          ActivityCompat.requestPermissions(LocationActivity.this, PERMISSIONS_LOCATION,
              REQUEST_LOCATION);
        }
      }).show();
    } else {
      ActivityCompat.requestPermissions(this, PERMISSIONS_LOCATION, REQUEST_LOCATION);
    }
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    if (requestCode == REQUEST_LOCATION) {
      if ((grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) || (
          grantResults.length == 2
              && (grantResults[0] == PackageManager.PERMISSION_GRANTED
              || grantResults[1] == PackageManager.PERMISSION_GRANTED))) {
        onConnected(null);
      } else {
        Snackbar.make(relativeLayout, R.string.permission_location_not_granted,
            Snackbar.LENGTH_INDEFINITE).show();
      }
    } else {
      super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
  }
}


