package net.gideonbros.dailymeal.util;

import java.util.List;
import net.gideonbros.dailymeal.data.models.DailyMealModel;
import net.gideonbros.dailymeal.util.GenerateDataUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Matija on 7.3.2017..
 */

public class GeneralDataUtilTest {

  @Test public void testGetDailyMeals() {
    List<DailyMealModel> dailyMeals = GenerateDataUtil.getDailyMeals();
    int actual = dailyMeals.size();
    int expected = 7;

    assertEquals("GeneralDataUtilTest size failed", expected, actual);
  }
}
