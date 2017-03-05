package net.gideonbros.dailymeal.dagger;

import net.gideonbros.dailymeal.DrawerActivity;
import net.gideonbros.dailymeal.RestaurantActivity;
import net.gideonbros.dailymeal.presentation.presenter.DailyMealPresenterImp;
import net.gideonbros.dailymeal.presentation.presenter.RestaurantPresenterImp;
import net.gideonbros.dailymeal.service.NetworkReceiver;
import net.gideonbros.dailymeal.service.DailyMealService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Matija on 3.3.2017..
 */

@Singleton
@Component(modules = {AppModule.class})
public interface IAppComponent {
    void inject(DrawerActivity activity);
    void inject(DailyMealPresenterImp presenter);

    void inject(RestaurantActivity activity);
    void inject(RestaurantPresenterImp presenter);

    void inject(DailyMealService service);
    void inject(NetworkReceiver receiver);
}
