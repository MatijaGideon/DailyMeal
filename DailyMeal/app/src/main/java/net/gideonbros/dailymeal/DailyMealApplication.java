package net.gideonbros.dailymeal;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import net.gideonbros.dailymeal.dagger.AppModule;
import net.gideonbros.dailymeal.dagger.DaggerIAppComponent;
import net.gideonbros.dailymeal.dagger.IAppComponent;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Matija on 3.3.2017..
 */

public class DailyMealApplication extends Application {
    private IAppComponent mComponent;

    @Override public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Stetho.initialize(Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                    .build());
        }

        Realm.init(this);
        RealmConfiguration config =
                new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);

        mComponent = DaggerIAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public IAppComponent getComponent() {
        return mComponent;
    }
}
