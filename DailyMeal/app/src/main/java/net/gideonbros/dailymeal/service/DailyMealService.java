package net.gideonbros.dailymeal.service;

import android.util.Log;

import net.gideonbros.dailymeal.dagger.IAppComponent;
import net.gideonbros.dailymeal.data.models.DailyMealModel;

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

public class DailyMealService {
    private static final String TAG = DailyMealService.class.getSimpleName();

    @Inject
    RetrofitApiService apiService;

    @Inject
    Realm realmInstance;

    public DailyMealService(IAppComponent appComponent) {
        appComponent.inject(this);
    }

    public RealmResults<DailyMealModel> getLocalDailyMeals() {
        return realmInstance.where(DailyMealModel.class).findAll();
    }

    public void getDailyMealsAsync(Double latitude, Double longitude) {
        Call<List<DailyMealModel>> call = apiService.getDailyMeal(latitude, longitude);
        call.enqueue(new Callback<List<DailyMealModel>>() {
            @Override
            public void onResponse(Call<List<DailyMealModel>> call, Response<List<DailyMealModel>> response) {
                List<DailyMealModel> list = response.body();
                Log.d(TAG, "onResponse: " + list);
                realmInstance.beginTransaction();
                realmInstance.copyToRealmOrUpdate(list);
                realmInstance.commitTransaction();
            }

            @Override public void onFailure(Call<List<DailyMealModel>> call, Throwable t) {

            }
        });
    }
}
