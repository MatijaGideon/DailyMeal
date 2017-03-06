package net.gideonbros.dailymeal.service;

import net.gideonbros.dailymeal.data.models.DailyMealModel;
import net.gideonbros.dailymeal.data.models.RestaurantModel;

import io.realm.RealmResults;

/**
 * Created by Matija on 4.3.2017..
 */

public interface IDailyMealService {
    void getDailyMealsAsync(Double latitude, Double longitude, Integer range, Integer maxNumberOfMeals);
    void getGeneratedDailyMealsAsync(Double latitude, Double longitude, Integer range, Integer maxNumberOfMeals);
    RealmResults<DailyMealModel> getLocalDailyMeals(String searchString);
    RestaurantModel getRestaurantById(int id);
}
