package net.gideonbros.dailymeal.service;

import android.util.Log;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.realm.RealmResults;
import java.util.List;
import javax.inject.Inject;
import net.gideonbros.dailymeal.dagger.IAppComponent;
import net.gideonbros.dailymeal.data.models.DailyMealModel;
import net.gideonbros.dailymeal.data.models.RestaurantModel;
import net.gideonbros.dailymeal.data.repositories.DailyMealRepository;
import net.gideonbros.dailymeal.service.network.RetrofitApiService;
import net.gideonbros.dailymeal.util.GenerateDataUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Matija on 3.3.2017..
 */

public class DailyMealService implements IDailyMealService {
  private static final String TAG = DailyMealService.class.getSimpleName();

  @Inject RetrofitApiService apiService;

  @Inject DailyMealRepository repository;

  @Inject Completable completable;

  public DailyMealService(IAppComponent appComponent) {
    appComponent.inject(this);
  }

  public RealmResults<DailyMealModel> getLocalDailyMeals(String searchString) {
    return repository.getData(searchString);
  }

  @Override public RestaurantModel getRestaurantById(int id) {
    return repository.getRestaurant(id);
  }

  public void getDailyMealsAsync(Double latitude, Double longitude, Integer range,
      Integer maxNumberOfMeals) {
    Call<List<DailyMealModel>> call =
        apiService.getDailyMeal(latitude, longitude, range, maxNumberOfMeals);
    call.enqueue(new Callback<List<DailyMealModel>>() {
      @Override public void onResponse(Call<List<DailyMealModel>> call,
          Response<List<DailyMealModel>> response) {
        List<DailyMealModel> list = response.body();
        Log.d(TAG, "onResponse: " + list);
        repository.saveData(list);
      }

      @Override public void onFailure(Call<List<DailyMealModel>> call, Throwable t) {
        Log.d(TAG, " onError : " + t.getMessage());
      }
    });
  }

  public void getGeneratedDailyMealsAsync(Double latitude, Double longitude, Integer range,
      Integer maxNumberOfMeals) {
    completable.subscribe(getCompletableObserver());
  }

  private CompletableObserver getCompletableObserver() {
    return new CompletableObserver() {
      @Override public void onSubscribe(Disposable d) {
        Log.d(TAG, " onSubscribe : " + d.isDisposed());
      }

      @Override public void onComplete() {
        repository.saveData(GenerateDataUtil.getDailyMeals());
      }

      @Override public void onError(Throwable e) {
        Log.d(TAG, " onError : " + e.getMessage());
      }
    };
  }
}
