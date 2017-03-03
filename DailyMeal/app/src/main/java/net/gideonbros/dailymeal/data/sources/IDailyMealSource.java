package net.gideonbros.dailymeal.data.sources;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.gideonbros.dailymeal.data.models.DailyMealModel;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by Matija on 3.3.2017..
 */

public interface IDailyMealSource {
    @Nullable
    List<DailyMealModel> getData();

    @Nullable
    DailyMealModel getData(@NonNull int id);

    void saveData(@NonNull RealmResults<DailyMealModel> model);

    void deleteData(@NonNull DailyMealModel model);
}
