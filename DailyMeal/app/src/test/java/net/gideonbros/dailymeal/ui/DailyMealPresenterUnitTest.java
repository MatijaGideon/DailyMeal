package net.gideonbros.dailymeal.ui;

import io.reactivex.Completable;
import net.gideonbros.dailymeal.dagger.IAppComponent;
import net.gideonbros.dailymeal.data.repositories.DailyMealRepository;
import net.gideonbros.dailymeal.presentation.presenter.DailyMealPresenterImp;
import net.gideonbros.dailymeal.service.DailyMealService;
import net.gideonbros.dailymeal.service.RetrofitApiService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Matija on 3/8/2017.
 */

@RunWith(MockitoJUnitRunner.class) public class DailyMealPresenterUnitTest {
  @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock DailyMealService service;
  @Mock Completable completable;
  @Mock IAppComponent appComponent;

  @InjectMocks DailyMealPresenterImp presenter;

  @Test public void shouldGetDataOnlyOnce() {

  }
}