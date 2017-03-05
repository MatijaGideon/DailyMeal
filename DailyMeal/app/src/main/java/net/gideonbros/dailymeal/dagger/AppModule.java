package net.gideonbros.dailymeal.dagger;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import net.gideonbros.dailymeal.DailyMealApplication;
import net.gideonbros.dailymeal.data.repositories.DailyMealRepository;
import net.gideonbros.dailymeal.data.sources.DailyMealSource;
import net.gideonbros.dailymeal.data.sources.IDailyMealSource;
import net.gideonbros.dailymeal.presentation.presenter.DailyMealPresenterImp;
import net.gideonbros.dailymeal.presentation.presenter.IDailyMealPresenter;
import net.gideonbros.dailymeal.presentation.presenter.IRestaurantPresenter;
import net.gideonbros.dailymeal.presentation.presenter.RestaurantPresenterImp;
import net.gideonbros.dailymeal.service.DailyMealService;
import net.gideonbros.dailymeal.service.IDailyMealService;
import net.gideonbros.dailymeal.service.RetrofitApiService;
import net.gideonbros.dailymeal.service.RetrofitManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by Matija on 3.3.2017..
 */

@Module
public class AppModule {

    private DailyMealApplication app;

    public AppModule(DailyMealApplication app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public DailyMealApplication provideApplicationContext() {
        return app;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPrefs() {
        return PreferenceManager.getDefaultSharedPreferences(app);
    }

    @Provides
    @Singleton
    Realm providesRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    @Singleton
    IDailyMealSource providesSource(Realm realm) {
        return new DailyMealSource(realm);
    }

    @Provides
    @Singleton
    DailyMealRepository providesRepository(IDailyMealSource source) {
        return new DailyMealRepository(source);
    }

    @Provides
    @Singleton
    public RetrofitApiService provideApiService() {
        return RetrofitManager.getService();
    }

    @Provides
    @Singleton
    IDailyMealService provideDailyMealService() {
        return new DailyMealService(app.getComponent());
    }

    @Provides
    @Singleton
    IDailyMealPresenter provideDailyMealPresenter() {
        return new DailyMealPresenterImp(app.getComponent());
    }

    @Provides
    @Singleton
    IRestaurantPresenter provideRestaurantPresenter() {
        return new RestaurantPresenterImp(app.getComponent());
    }

}

