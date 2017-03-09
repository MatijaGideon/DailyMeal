package net.gideonbros.dailymeal.presentation.presenter;

import android.support.annotation.NonNull;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;
import net.gideonbros.dailymeal.dagger.IAppComponent;
import net.gideonbros.dailymeal.presentation.view.IDailyMealView;
import net.gideonbros.dailymeal.service.IDailyMealService;

/**
 * Created by Matija on 3.3.2017..
 */

public class DailyMealPresenterImp implements IDailyMealPresenter {

  private static final Integer RANGE = 20;
  private static final Integer MAX_NUM_OF_RESULTS = 30;
  private static final String EMPTY_STRING = "";

  @Inject IDailyMealService service;

  @Inject Completable completable;

  private IDailyMealView view;

  public DailyMealPresenterImp(@NonNull IAppComponent appComponent) {
    appComponent.inject(this);
  }

  @Override public void setView(IDailyMealView view) {
    this.view = view;
    this.view.initDailyMeals(service.getLocalDailyMeals(EMPTY_STRING));
  }

  @Override public void removeView(IDailyMealView view) {
    this.view = null;
  }

  @Override public void startCollectingData(Double latitude, Double longitude) {
    //real API service
    //service.getDailyMealsAsync(latitude, longitude, RANGE, MAX_NUM_OF_RESULTS);
    completable.subscribe(getCompletableObserver());
    service.getGeneratedDailyMealsAsync(latitude, longitude, RANGE, MAX_NUM_OF_RESULTS);
  }

  @Override public void filterData(String searchString) {
    this.view.showDailyMeals(service.getLocalDailyMeals(searchString));
  }

  private CompletableObserver getCompletableObserver() {
    return new CompletableObserver() {
      @Override public void onSubscribe(Disposable d) {
        view.showProgressBar();
      }

      @Override public void onComplete() {
        view.hideProgressBar();
      }

      @Override public void onError(Throwable e) {
      }
    };
  }
}
