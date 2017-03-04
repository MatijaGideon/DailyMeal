package net.gideonbros.dailymeal.data.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Matija on 3.3.2017..
 */

public class DailyMealModel extends RealmObject {
    @PrimaryKey
    private int id;
    private String dailyMealName;
    private String dailyMealDescription;
    private String dailyMealImageUrl;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantContactNumber;
    private String restaurantImageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDailyMealName() {
        return dailyMealName;
    }

    public void setDailyMealName(String dailyMealName) {
        this.dailyMealName = dailyMealName;
    }

    public String getDailyMealDescription() {
        return dailyMealDescription;
    }

    public void setDailyMealDescription(String dailyMealDescription) {
        this.dailyMealDescription = dailyMealDescription;
    }

    public String getDailyMealImageUrl() {
        return dailyMealImageUrl;
    }

    public void setDailyMealImageUrl(String dailyMealImageUrl) {
        this.dailyMealImageUrl = dailyMealImageUrl;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantContactNumber() {
        return restaurantContactNumber;
    }

    public void setRestaurantContactNumber(String restaurantContactNumber) {
        this.restaurantContactNumber = restaurantContactNumber;
    }

    public String getRestaurantImageUrl() {
        return restaurantImageUrl;
    }

    public void setRestaurantImageUrl(String restaurantImageUrl) {
        this.restaurantImageUrl = restaurantImageUrl;
    }
}
