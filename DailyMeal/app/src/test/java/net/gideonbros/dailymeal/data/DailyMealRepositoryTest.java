package net.gideonbros.dailymeal.data;

import net.gideonbros.dailymeal.data.repositories.DailyMealRepository;
import net.gideonbros.dailymeal.data.sources.IDailyMealSource;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Matija on 3/8/2017.
 */

public class DailyMealRepositoryTest {
  @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

  @Mock IDailyMealSource source;

  DailyMealRepository repository;

  @Before
  public void init(){
    repository = new DailyMealRepository(source);
  }

  @Test
  public void shouldGetDataOnlyOnce() {
    try {
      repository.getData(anyString());
      verify(source, times(1)).getData(anyString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void shouldGetDataByIdOnlyOnce() {
    try {
      repository.getData(anyInt());
      verify(source, times(1)).getData(anyInt());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void shouldGetRestaurantByIdOnce() {
    try {
      repository.getRestaurant(anyInt());
      verify(source, times(1)).getRestaurant(anyInt());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
