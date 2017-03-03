package net.gideonbros.dailymeal.dagger;

import net.gideonbros.dailymeal.DailyMealActivity;
import net.gideonbros.dailymeal.presentation.presenter.DailyMealPresenterImp;
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
    void inject(DailyMealService service);
    void inject(DailyMealPresenterImp presenter);
}
