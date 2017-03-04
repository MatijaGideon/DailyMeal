package net.gideonbros.dailymeal;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import net.gideonbros.dailymeal.data.models.DailyMealModel;
import net.gideonbros.dailymeal.presentation.presenter.IRestaurantPresenter;
import net.gideonbros.dailymeal.presentation.view.IRestaurantView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Matija on 4.3.2017..
 */

public class RestaurantActivity extends AppCompatActivity implements IRestaurantView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.place_detail)
    TextView detail;
    @BindView(R.id.place_location)
    TextView location;
    @BindView(R.id.image)
    ImageView image;

    @Inject
    IRestaurantPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_restaurant);

        ((DailyMealApplication) getApplication())
                .getComponent()
                .inject(this);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbar.setTitle("Title");
        detail.setText("Detail");
        location.setText("Location");
        image.setImageDrawable(getResources().getDrawable(R.drawable.a, getTheme()));

        presenter.setView(this, 1);
    }

    @Override
    public void showRestaurantInfo(@NonNull DailyMealModel dailyMealModel) {

    }
}
