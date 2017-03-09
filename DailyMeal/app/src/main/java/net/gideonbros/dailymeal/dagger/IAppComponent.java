package net.gideonbros.dailymeal.dagger;

import dagger.Component;
import javax.inject.Singleton;
import net.gideonbros.dailymeal.presentation.presenter.DailyMealPresenterImp;
import net.gideonbros.dailymeal.presentation.presenter.RestaurantPresenterImp;
import net.gideonbros.dailymeal.presentation.view.activities.DailyMealActivity;
import net.gideonbros.dailymeal.presentation.view.activities.RestaurantActivity;
import net.gideonbros.dailymeal.service.DailyMealService;

/**
 * Created by Matija on 3.3.2017..
 */

@Singleton @Component(modules = { AppModule.class }) public interface IAppComponent {
  void inject(DailyMealActivity activity);

  void inject(DailyMealPresenterImp presenter);

  void inject(RestaurantActivity activity);

  void inject(RestaurantPresenterImp presenter);

  void inject(DailyMealService service);
}
