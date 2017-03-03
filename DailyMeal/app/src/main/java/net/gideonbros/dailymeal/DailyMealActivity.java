package net.gideonbros.dailymeal;

import android.os.Bundle;
import android.support.annotation.NonNull;

import net.gideonbros.dailymeal.data.models.DailyMealModel;
import net.gideonbros.dailymeal.presentation.view.IDailyMealView;

import io.realm.RealmResults;

public class DailyMealActivity extends DrawerActivity implements IDailyMealView {

    @Override
    int getLayoutId() {
        return R.layout.activity_daily_meal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void showDailyMeals(@NonNull RealmResults<DailyMealModel> dailyMealModels) {

    }
}
