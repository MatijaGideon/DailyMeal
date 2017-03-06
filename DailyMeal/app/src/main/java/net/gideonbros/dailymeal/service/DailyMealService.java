package net.gideonbros.dailymeal.service;

import android.util.Log;
import io.realm.RealmResults;
import java.util.List;
import javax.inject.Inject;
import net.gideonbros.dailymeal.dagger.IAppComponent;
import net.gideonbros.dailymeal.data.models.DailyMealModel;
import net.gideonbros.dailymeal.data.repositories.DailyMealRepository;
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

      }
    });
  }

  public void getGeneratedDailyMealsAsync(Double latitude, Double longitude, Integer range,
      Integer maxNumberOfMeals) {

  }
}
