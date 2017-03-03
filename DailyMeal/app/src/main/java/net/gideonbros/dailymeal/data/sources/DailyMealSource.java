package net.gideonbros.dailymeal.data.sources;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.gideonbros.dailymeal.data.models.DailyMealModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Matija on 3.3.2017..
 */
@Singleton
public class DailyMealSource implements IDailyMealSource {

    private Realm mRealm;

    @Inject
    public DailyMealSource(@NonNull Realm realm) {
        mRealm = realm;
    }

    @Nullable
    @Override
    public List<DailyMealModel> getData() {
        return mRealm.where(DailyMealModel.class).findAll();
    }

    @Nullable
    @Override
    public DailyMealModel getData(@NonNull int id) {
        return mRealm.where(DailyMealModel.class).equalTo("id", id).findFirst();
    }

    @Override
    public void saveData(@NonNull RealmResults<DailyMealModel> list) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(list);
        mRealm.commitTransaction();
    }

    @Override
    public void deleteData(@NonNull DailyMealModel model) {

    }
}
