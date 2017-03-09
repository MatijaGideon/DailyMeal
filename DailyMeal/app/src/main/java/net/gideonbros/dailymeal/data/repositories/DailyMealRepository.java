package net.gideonbros.dailymeal.data.repositories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import io.realm.RealmResults;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import net.gideonbros.dailymeal.data.models.DailyMealModel;
import net.gideonbros.dailymeal.data.models.RestaurantModel;
import net.gideonbros.dailymeal.data.sources.IDailyMealSource;

/**
 * Created by Matija on 3.3.2017..
 */
@Singleton public class DailyMealRepository implements IDailyMealSource {
  private final IDailyMealSource mDataSource;

  @Inject public DailyMealRepository(IDailyMealSource dataSource) {
    mDataSource = dataSource;
  }

  @Override public RealmResults<DailyMealModel> getData(String searchString) {
    return mDataSource.getData(searchString);
  }

  @Nullable @Override public DailyMealModel getData(int id) {
    return mDataSource.getData(id);
  }

  @Nullable @Override public RestaurantModel getRestaurant(int id) {
    return mDataSource.getRestaurant(id);
  }

  @Override public void saveData(@NonNull List<DailyMealModel> model) {
    mDataSource.saveData(model);
  }

  @Override public void deleteData(@NonNull DailyMealModel model) {
    mDataSource.deleteData(model);
  }
}
