package net.gideonbros.dailymeal.service.network;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import net.gideonbros.dailymeal.data.models.DailyMealModel;
import net.gideonbros.dailymeal.data.models.RestaurantModel;

/**
 * Created by Matija on 3.3.2017..
 */

public class JsonSerializationAdapter implements JsonDeserializer<DailyMealModel> {

  @Override public DailyMealModel deserialize(JsonElement jsonElement, Type typeOfT,
      JsonDeserializationContext context) throws JsonParseException {
    DailyMealModel dailyMealModel = null;

    //TODO: check implementation!
    try {
      if (jsonElement.isJsonObject()) {
        JsonObject dailyMealObject = (JsonObject) jsonElement;

        JsonElement restaurantElement = dailyMealObject.get("restaurant");
        RestaurantModel restaurantModel = null;
        if (restaurantElement.isJsonObject()) {
          JsonObject restaurantObject = (JsonObject) restaurantElement;
          restaurantModel = context.deserialize(restaurantObject, RestaurantModel.class);
        }

        dailyMealModel = context.deserialize(dailyMealObject, DailyMealModel.class);
        dailyMealModel.setRestaurant(restaurantModel);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return dailyMealModel;
  }
}
