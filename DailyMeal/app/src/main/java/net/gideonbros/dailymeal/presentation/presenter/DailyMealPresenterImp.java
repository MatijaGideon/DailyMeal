package net.gideonbros.dailymeal.presentation.presenter;

import android.support.annotation.NonNull;

import net.gideonbros.dailymeal.dagger.IAppComponent;
import net.gideonbros.dailymeal.presentation.view.IDailyMealView;
import net.gideonbros.dailymeal.service.DailyMealService;

import javax.inject.Inject;

/**
 * Created by Matija on 3.3.2017..
 */

public class DailyMealPresenterImp implements IDailyMealPresenter {

    @Inject
    DailyMealService mService;

    private IDailyMealView view;

    public DailyMealPresenterImp(@NonNull IAppComponent appComponent) {
        appComponent.inject(this);
    }

    @Override
    public void setView(IDailyMealView view) {
        this.view = view;
        this.view.showDailyMeals(mService.getLocalDailyMeals());
        //mService.fetchNotificationsAsync("London", this);
    }
}
