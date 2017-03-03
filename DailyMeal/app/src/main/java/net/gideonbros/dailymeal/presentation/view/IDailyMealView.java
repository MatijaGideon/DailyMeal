package net.gideonbros.dailymeal.presentation.view;

import android.support.annotation.NonNull;

import net.gideonbros.dailymeal.data.models.DailyMealModel;

import io.realm.RealmResults;

/**
 * Created by Matija on 3.3.2017..
 */

public interface IDailyMealView {
    void showDailyMeals(@NonNull RealmResults<DailyMealModel> dailyMealModels);
}
