package net.gideonbros.dailymeal.service;

import net.gideonbros.dailymeal.data.models.DailyMealModel;

import io.realm.RealmResults;

/**
 * Created by Matija on 4.3.2017..
 */

public interface IDailyMealService {
    void getDailyMealsAsync(Double latitude, Double longitude);
    RealmResults<DailyMealModel> getLocalDailyMeals(String searchString);
    DailyMealModel getLocalDailyMealsById(int id);
}
