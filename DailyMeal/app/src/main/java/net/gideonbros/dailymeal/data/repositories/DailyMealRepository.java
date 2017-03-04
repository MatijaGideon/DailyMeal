package net.gideonbros.dailymeal.data.repositories;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.gideonbros.dailymeal.data.models.DailyMealModel;
import net.gideonbros.dailymeal.data.sources.IDailyMealSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.RealmResults;

/**
 * Created by Matija on 3.3.2017..
 */
@Singleton
public class DailyMealRepository implements IDailyMealSource {
    private final IDailyMealSource mSecondDataSource;

    @Inject
    public DailyMealRepository(IDailyMealSource dataSource) {
        mSecondDataSource = dataSource;
    }

    @Override
    public RealmResults<DailyMealModel> getData() {
        return mSecondDataSource.getData();
    }

    @Nullable
    @Override
    public DailyMealModel getData(@NonNull int id) {
        return mSecondDataSource.getData(id);
    }

    @Override
    public void saveData(@NonNull RealmResults<DailyMealModel> model) {
        mSecondDataSource.saveData(model);
    }

    @Override
    public void deleteData(@NonNull DailyMealModel model) {
        mSecondDataSource.deleteData(model);
    }
}
