package net.gideonbros.dailymeal.service;

import net.gideonbros.dailymeal.data.models.DailyMealModel;

import java.util.List;

import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Matija on 3.3.2017..
 */

public interface RetrofitApiService {
    @GET("api/dailymeal?latitude=X&longitude=Y")
    Call<RealmResults<DailyMealModel>> getDailyMeal(@Query("X") Double latitude, @Query("Y") Double longitude);
}

