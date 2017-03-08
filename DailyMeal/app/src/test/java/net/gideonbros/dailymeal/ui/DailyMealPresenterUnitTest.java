package net.gideonbros.dailymeal.ui;

import io.reactivex.Completable;
import io.realm.RealmResults;
import net.gideonbros.dailymeal.dagger.IAppComponent;
import net.gideonbros.dailymeal.presentation.presenter.DailyMealPresenterImp;
import net.gideonbros.dailymeal.presentation.view.IDailyMealView;
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

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

/**
 * Created by Matija on 3/8/2017.
 */

@RunWith(MockitoJUnitRunner.class) public class DailyMealPresenterUnitTest {
  @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock DailyMealService service;
  @Mock Completable completable;
  @Mock IDailyMealView view;
  @Mock IAppComponent appComponent;

  @InjectMocks DailyMealPresenterImp presenter;

  @Before public void init() {

  }

  @Test public void shouldSetView_CallInitDailyMeals() {
    presenter.setView(view);
    verify(view, atLeastOnce()).initDailyMeals(any(RealmResults.class));
  }

  @Test public void shouldFilterData_CallShowDailyMeals() {
    presenter.filterData(anyString());
    verify(view, atLeastOnce()).showDailyMeals(any(RealmResults.class));
  }

}