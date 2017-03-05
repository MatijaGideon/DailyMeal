package net.gideonbros.dailymeal.data.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Matija on 3.3.2017..
 */

public class DailyMealModel extends RealmObject {
    @PrimaryKey
    int id;
    @NonNull
    String dailyMealName;
    @NonNull
    String dailyMealDescription;
    @NonNull
    String dailyMealImageUrl;
    @Nullable
    String dailyMealTopReview;
    @NonNull
    RestaurantModel restaurant;

    public DailyMealModel(int id, int restaurantId, @NonNull String dailyMealName, @NonNull String dailyMealDescription, @NonNull String dailyMealImageUrl, String dailyMealTopReview, @NonNull RestaurantModel restaurant) {
        this.id = id;
        this.dailyMealName = dailyMealName;
        this.dailyMealDescription = dailyMealDescription;
        this.dailyMealImageUrl = dailyMealImageUrl;
        this.dailyMealTopReview = dailyMealTopReview;
        this.restaurant = restaurant;
    }

    @Nullable
    public String getDailyMealTopReview() {
        return dailyMealTopReview;
    }

    @NonNull
    public String getDailyMealName() {
        return dailyMealName;
    }

    @NonNull
    public String getDailyMealDescription() {
        return dailyMealDescription;
    }

    @NonNull
    public String getDailyMealImageUrl() {
        return dailyMealImageUrl;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public RestaurantModel getRestaurant() {
        return restaurant;
    }
}
