package net.gideonbros.dailymeal.presentation.presenter;

import android.support.annotation.NonNull;
import javax.inject.Inject;
import net.gideonbros.dailymeal.dagger.IAppComponent;
import net.gideonbros.dailymeal.presentation.view.IRestaurantView;
import net.gideonbros.dailymeal.service.IDailyMealService;

/**
 * Created by Matija on 4.3.2017..
 */

public class RestaurantPresenterImp implements IRestaurantPresenter {

  @Inject IDailyMealService service;

  IRestaurantView view;

  public RestaurantPresenterImp(@NonNull IAppComponent appComponent) {
    appComponent.inject(this);
  }

  @Override public void setView(IRestaurantView view, int restaurantId) {
    this.view = view;
    this.view.showRestaurantInfo(service.getRestaurantById(restaurantId));
  }
}
