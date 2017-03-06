package net.gideonbros.dailymeal.data.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Matija on 5.3.2017..
 */

public class RestaurantModel extends RealmObject {

  @PrimaryKey int id;
  @NonNull String restaurantName;
  @NonNull String restaurantAddress;
  @NonNull String restaurantNumber;
  @NonNull String restaurantImageUrl;
  @Nullable String restaurantEmail;
  @Nullable String restaurantWebPage;

  public RestaurantModel() {
  }

  public RestaurantModel(int id, @NonNull String restaurantName, @NonNull String restaurantAddress,
      @NonNull String restaurantContactNumber, @NonNull String restaurantImageUrl,
      String restaurantEmail, String restaurantWebPage) {
    this.restaurantWebPage = restaurantWebPage;
    this.id = id;
    this.restaurantName = restaurantName;
    this.restaurantAddress = restaurantAddress;
    this.restaurantNumber = restaurantContactNumber;
    this.restaurantImageUrl = restaurantImageUrl;
    this.restaurantEmail = restaurantEmail;
  }

  @NonNull public String getRestaurantName() {
    return restaurantName;
  }

  @NonNull public String getRestaurantAddress() {
    return restaurantAddress;
  }

  @NonNull public String getRestaurantNumber() {
    return restaurantNumber;
  }

  @NonNull public String getRestaurantImageUrl() {
    return restaurantImageUrl;
  }

  @Nullable public String getRestaurantEmail() {
    return restaurantEmail;
  }

  @Nullable public String getRestaurantWebPage() {
    return restaurantWebPage;
  }

  public int getId() {
    return id;
  }
}
