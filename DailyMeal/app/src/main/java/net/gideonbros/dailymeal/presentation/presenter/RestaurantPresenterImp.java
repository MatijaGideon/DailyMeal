package net.gideonbros.dailymeal.presentation.presenter;

import android.support.annotation.NonNull;

import net.gideonbros.dailymeal.dagger.IAppComponent;
import net.gideonbros.dailymeal.presentation.view.IRestaurantView;
import net.gideonbros.dailymeal.service.DailyMealService;
import net.gideonbros.dailymeal.service.IDailyMealService;

import javax.inject.Inject;

/**
 * Created by Matija on 4.3.2017..
 */

public class RestaurantPresenterImp implements IRestaurantPresenter {

    @Inject
    IDailyMealService service;

    private IRestaurantView view;

    public RestaurantPresenterImp(@NonNull IAppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void setView(IRestaurantView view, int dailyMealId) {
        this.view = view;
        //this.view.showRestaurantInfo(service.getLocalDailyMealsById(dailyMealId));
    }

}
