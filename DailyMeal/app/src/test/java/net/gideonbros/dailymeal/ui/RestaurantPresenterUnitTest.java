package net.gideonbros.dailymeal.ui;

import net.gideonbros.dailymeal.dagger.IAppComponent;
import net.gideonbros.dailymeal.presentation.presenter.RestaurantPresenterImp;
import net.gideonbros.dailymeal.presentation.view.IRestaurantView;
import net.gideonbros.dailymeal.service.DailyMealService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

/**
 * Created by Matija on 3/8/2017.
 */

@RunWith(MockitoJUnitRunner.class) public class RestaurantPresenterUnitTest {
  @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock DailyMealService service;
  @Mock IRestaurantView view;
  @Mock IAppComponent appComponent;

  @InjectMocks RestaurantPresenterImp presenter;

  @Before public void init() {
  }

  @Test public void shouldSetView_CallInitDailyMeals() {
    presenter.setView(view, anyInt());
    verify(view, atLeastOnce()).showRestaurantInfo(service.getRestaurantById(anyInt()));
  }
}