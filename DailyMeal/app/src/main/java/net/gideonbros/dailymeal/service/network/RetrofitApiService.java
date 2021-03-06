package net.gideonbros.dailymeal.service.network;

import java.util.List;
import net.gideonbros.dailymeal.data.models.DailyMealModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Matija on 3.3.2017..
 */

public interface RetrofitApiService {
  @GET("api/dailymeal?latitude=X&longitude=Y&range=Z&max=W")
  Call<List<DailyMealModel>> getDailyMeal(@Query("X") Double latitude, @Query("Y") Double longitude,
      @Query("Z") Integer range, @Query("W") Integer maxNumberOfMeals);
}

