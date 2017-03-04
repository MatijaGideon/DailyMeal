package net.gideonbros.dailymeal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import net.gideonbros.dailymeal.data.models.DailyMealModel;
import net.gideonbros.dailymeal.presentation.presenter.DailyMealPresenterImp;
import net.gideonbros.dailymeal.presentation.presenter.IDailyMealPresenter;
import net.gideonbros.dailymeal.presentation.view.IDailyMealView;
import net.gideonbros.dailymeal.presentation.view.adapter.DailyMealRecyclerAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

public class DailyMealActivity extends DrawerActivity implements IDailyMealView {

    @BindView(R.id.daily_meal_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.content_daily_meal)
    RelativeLayout relativeLayout;

    @Inject
    IDailyMealPresenter presenter;

    private DailyMealRecyclerAdapter adapter;

    @Override
    int getLayoutId() {
        return R.layout.activity_daily_meal;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((DailyMealApplication) getApplication())
                .getComponent()
                .inject(this);
        ButterKnife.bind(this);
        initRecyclerView();
        presenter.setView(this);
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new DailyMealRecyclerAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showWelcomeMessage() {
        Snackbar snackbar =
                Snackbar.make(relativeLayout, getString(R.string.welcome), Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void showDailyMeals(@NonNull RealmResults<DailyMealModel> dailyMealModels) {

    }

}
