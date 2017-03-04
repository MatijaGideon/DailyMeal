package net.gideonbros.dailymeal.dagger;

import net.gideonbros.dailymeal.DailyMealActivity;
import net.gideonbros.dailymeal.DailyMealApplication;
import net.gideonbros.dailymeal.RestaurantActivity;
import net.gideonbros.dailymeal.presentation.presenter.DailyMealPresenterImp;
import net.gideonbros.dailymeal.presentation.presenter.IDailyMealPresenter;
import net.gideonbros.dailymeal.presentation.presenter.IRestaurantPresenter;
import net.gideonbros.dailymeal.presentation.presenter.RestaurantPresenterImp;
import net.gideonbros.dailymeal.service.DailyMealService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Matija on 3.3.2017..
 */

@Singleton
@Component(modules = {AppModule.class})
public interface IAppComponent {
    void inject(DailyMealActivity activity);
    void inject(DailyMealPresenterImp presenter);

    void inject(RestaurantActivity activity);
    void inject(RestaurantPresenterImp presenter);

    void inject(DailyMealService service);
}
