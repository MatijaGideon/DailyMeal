package net.gideonbros.dailymeal.data.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Matija on 5.3.2017..
 */

public class RestaurantModel extends RealmObject {

    @PrimaryKey
    int id;
    @NonNull
    String restaurantName;
    @NonNull
    String restaurantAddress;
    @NonNull
    String restaurantContactNumber;
    @NonNull
    String restaurantImageUrl;
    @Nullable
    String restaurantEmail;
    @Nullable
    String restaurantWebPage;

    public RestaurantModel(int id, @NonNull String restaurantName, @NonNull String restaurantAddress, @NonNull String restaurantContactNumber, @NonNull String restaurantImageUrl, String restaurantEmail, String restaurantWebPage) {
        this.restaurantWebPage = restaurantWebPage;
        this.id = id;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantContactNumber = restaurantContactNumber;
        this.restaurantImageUrl = restaurantImageUrl;
        this.restaurantEmail = restaurantEmail;
    }
}
