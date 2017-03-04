package net.gideonbros.dailymeal.service;

import android.util.Log;

import net.gideonbros.dailymeal.dagger.IAppComponent;
import net.gideonbros.dailymeal.data.models.DailyMealModel;
import net.gideonbros.dailymeal.data.repositories.DailyMealRepository;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Matija on 3.3.2017..
 */

public class DailyMealService implements IDailyMealService {
    private static final String TAG = DailyMealService.class.getSimpleName();

    @Inject
    RetrofitApiService apiService;

    @Inject
    DailyMealRepository repository;

    public DailyMealService(IAppComponent appComponent) {
        appComponent.inject(this);
    }

    public RealmResults<DailyMealModel> getLocalDailyMeals() {
        return repository.getData();
    }

    @Override
    public DailyMealModel getLocalDailyMealsById(int id) {
        return repository.getData(id);
    }

    public void getDailyMealsAsync(Double latitude, Double longitude) {
        Call<RealmResults<DailyMealModel>> call = apiService.getDailyMeal(latitude, longitude);
        call.enqueue(new Callback<RealmResults<DailyMealModel>>() {
            @Override
            public void onResponse(Call<RealmResults<DailyMealModel>> call, Response<RealmResults<DailyMealModel>> response) {
                RealmResults<DailyMealModel> list = response.body();
                Log.d(TAG, "onResponse: " + list);
                repository.saveData(list);;
            }

            @Override public void onFailure(Call<RealmResults<DailyMealModel>> call, Throwable t) {

            }
        });
    }
}
