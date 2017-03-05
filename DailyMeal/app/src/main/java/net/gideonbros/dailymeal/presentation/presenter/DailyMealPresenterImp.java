package net.gideonbros.dailymeal.presentation.presenter;

import android.support.annotation.NonNull;

import net.gideonbros.dailymeal.dagger.IAppComponent;
import net.gideonbros.dailymeal.presentation.view.IDailyMealView;
import net.gideonbros.dailymeal.service.DailyMealService;
import net.gideonbros.dailymeal.service.IDailyMealService;

import javax.inject.Inject;

/**
 * Created by Matija on 3.3.2017..
 */

public class DailyMealPresenterImp implements IDailyMealPresenter {

    private static final String EMPTY_STRING = "";

    @Inject
    IDailyMealService service;

    private IDailyMealView view;

    public DailyMealPresenterImp(@NonNull IAppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void setView(IDailyMealView view) {
        this.view = view;
        this.view.showDailyMeals(service.getLocalDailyMeals(EMPTY_STRING));
    }

    @Override
    public void startCollectingData(Double latitude, Double longitude) {
        service.getDailyMealsAsync(latitude, longitude);
    }

    @Override
    public void filterData(String searchString) {
        this.view.showDailyMeals(service.getLocalDailyMeals(searchString));
    }
}
