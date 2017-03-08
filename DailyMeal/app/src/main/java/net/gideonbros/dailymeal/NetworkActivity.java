package net.gideonbros.dailymeal;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import net.gideonbros.dailymeal.service.NetworkReceiver;

/**
 * Created by Matija on 5.3.2017..
 */

public abstract class NetworkActivity extends LocationActivity
    implements NetworkReceiver.ConnectionListener {

  abstract void onNetworkConnected();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initCheckNetworkConnection();
  }

  @Override protected void onResume() {
    super.onResume();
    DailyMealApplication.getInstance().setConnectivityListener(this);
  }

  @Override public void onNetworkConnectionChanged(boolean isConnected) {
    showInfo(isConnected);
  }

  void initCheckNetworkConnection() {
    boolean isConnected = checkNetworkConnection();
    showInfo(isConnected);
  }

  boolean checkNetworkConnection() {
    return NetworkReceiver.isConnected();
  }

  void showInfo(boolean isConnected) {
    if (isConnected) {
      onNetworkConnected();
      Snackbar.make(relativeLayout, R.string.connected_to_network, Snackbar.LENGTH_SHORT).show();
    } else {
      Snackbar.make(relativeLayout, R.string.failed_to_connect_to_network,
          Snackbar.LENGTH_INDEFINITE).show();
    }
  }
}
