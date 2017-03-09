package net.gideonbros.dailymeal.presentation.view;

import android.support.annotation.NonNull;
import io.realm.RealmResults;
import net.gideonbros.dailymeal.data.models.DailyMealModel;

/**
 * Created by Matija on 3.3.2017..
 */

public interface IDailyMealView {
  void showWelcomeMessage();

  void initDailyMeals(@NonNull RealmResults<DailyMealModel> dailyMealModels);

  void showDailyMeals(@NonNull RealmResults<DailyMealModel> dailyMealModels);

  void showProgressBar();

  void hideProgressBar();
}
