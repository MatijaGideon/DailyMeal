package net.gideonbros.dailymeal.service;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import java.util.ArrayList;
import net.gideonbros.dailymeal.dagger.IAppComponent;
import net.gideonbros.dailymeal.data.repositories.DailyMealRepository;
import net.gideonbros.dailymeal.util.GenerateDataUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Matija on 3/8/2017.
 */

@RunWith(MockitoJUnitRunner.class) public class DailyMealServiceUnitTest {
  @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock RetrofitApiService rest;
  @Mock DailyMealRepository repository;
  @Mock Completable completable;
  @Mock IAppComponent appComponent;

  @InjectMocks DailyMealService service;

  @Test public void shouldGetDataOnlyOnce() {
    service.getLocalDailyMeals(anyString());
    verify(repository, times(1)).getData(anyString());
  }
}
