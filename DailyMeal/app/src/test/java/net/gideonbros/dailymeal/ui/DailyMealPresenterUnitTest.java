package net.gideonbros.dailymeal.ui;

import android.location.Location;
import android.support.annotation.NonNull;
import net.gideonbros.dailymeal.presentation.presenter.DailyMealPresenterImp;
import net.gideonbros.dailymeal.presentation.view.IDailyMealView;
import org.junit.Before;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * Created by Matija on 7.3.2017..
 */

public class DailyMealPresenterUnitTest {
  @Mock IDailyMealView view;
  @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
  DailyMealPresenterImp presenter;

  @Before public void init() {

  }

}
