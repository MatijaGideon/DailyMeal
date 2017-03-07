package net.gideonbros.dailymeal.data.sources;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.gideonbros.dailymeal.data.models.DailyMealModel;
import net.gideonbros.dailymeal.data.models.RestaurantModel;

/**
 * Created by Matija on 3.3.2017..
 */
@Singleton public class DailyMealSource implements IDailyMealSource {

  private Realm mRealm;

  @Inject public DailyMealSource(@NonNull Realm realm) {
    mRealm = realm;
  }

  @Nullable @Override public RealmResults<DailyMealModel> getData(String searchString) {
    if (searchString == null || "".equals(searchString)) {
      return mRealm.where(DailyMealModel.class).findAll().sort("dailyMealName");
    } else {
      return mRealm.where(DailyMealModel.class)
          .contains("dailyMealName", searchString, Case.INSENSITIVE)
          .or()
          .contains("restaurant.restaurantName", searchString, Case.INSENSITIVE)
          .or()
          .contains("restaurant.restaurantAddress", searchString, Case.INSENSITIVE)
          .findAll()
          .sort("dailyMealName");
    }
  }

  @Nullable @Override public DailyMealModel getData(int id) {
    return mRealm.where(DailyMealModel.class).equalTo("id", id).findFirst();
  }

  @Nullable @Override public RestaurantModel getRestaurant(int id) {
    return mRealm.where(DailyMealModel.class)
        .equalTo("restaurant.id", id)
        .findFirst()
        .getRestaurant();
  }

  @Override public void saveData(@NonNull List<DailyMealModel> list) {
    mRealm.beginTransaction();
    mRealm.copyToRealmOrUpdate(list);
    mRealm.commitTransaction();
  }

  @Override public void deleteData(@NonNull DailyMealModel model) {

  }
}
