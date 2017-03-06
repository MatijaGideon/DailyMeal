package net.gideonbros.dailymeal.data.sources;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.gideonbros.dailymeal.data.models.DailyMealModel;
import net.gideonbros.dailymeal.data.models.RestaurantModel;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by Matija on 3.3.2017..
 */

public interface IDailyMealSource {
    @Nullable
    RealmResults<DailyMealModel> getData(String searchString);

    @Nullable
    DailyMealModel getData(int id);

    @Nullable
    RestaurantModel getRestaurant(int id);

    void saveData(@NonNull List<DailyMealModel> model);

    void deleteData(@NonNull DailyMealModel model);
}
