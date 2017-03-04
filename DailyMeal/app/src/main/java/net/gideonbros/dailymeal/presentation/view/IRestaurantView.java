package net.gideonbros.dailymeal.presentation.view;

import android.support.annotation.NonNull;

import net.gideonbros.dailymeal.data.models.DailyMealModel;

import io.realm.RealmResults;

/**
 * Created by Matija on 4.3.2017..
 */

public interface IRestaurantView {
    void showRestaurantInfo(@NonNull DailyMealModel dailyMealModel);
}
