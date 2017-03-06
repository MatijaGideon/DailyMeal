package net.gideonbros.dailymeal.service;

import android.util.Log;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.RealmResults;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import net.gideonbros.dailymeal.dagger.IAppComponent;
import net.gideonbros.dailymeal.util.GenerateDataUtil;
import net.gideonbros.dailymeal.data.models.DailyMealModel;
import net.gideonbros.dailymeal.data.repositories.DailyMealRepository;
import net.gideonbros.dailymeal.util.RandomUtil;
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

  public DailyMealService(IAppComponent appComponent) {
    appComponent.inject(this);
  }

  public RealmResults<DailyMealModel> getLocalDailyMeals(String searchString) {
    return repository.getData(searchString);
  }

  @Override public DailyMealModel getLocalDailyMealsById(int id) {
    return repository.getData(id);
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

    final int min = 1;
    final int max = 3;

    Completable completable = Completable.timer(RandomUtil.getRandomNumber(min, max), TimeUnit.SECONDS);

    completable.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(getCompletableObserver());
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
