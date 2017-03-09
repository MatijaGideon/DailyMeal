package net.gideonbros.dailymeal.service.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.gideonbros.dailymeal.data.models.DailyMealModel;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Matija on 3.3.2017..
 */

public class RetrofitManager {
  private static final String API_ENDPOINT = "http://api.dailymeal.com";

  private static final String API_KEY = "e1ae0e51ead7122efda060f89b47d0d2";

  public static RetrofitApiService getService() {
    OkHttpClient client = new OkHttpClient();

    final GsonBuilder builder =
        new GsonBuilder().registerTypeAdapter(DailyMealModel.class, new JsonSerializationAdapter());

    final Gson gson = builder.create();

    Retrofit retrofit = new Retrofit.Builder().baseUrl(API_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build();

    RetrofitApiService service = retrofit.create(RetrofitApiService.class);
    return service;
  }
}
