package net.gideonbros.dailymeal.util;

import net.gideonbros.dailymeal.repeatrule.RepeatRule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Matija on 7.3.2017..
 */

public class RandomUtilTest {

  public final static int min1 = 1;
  public final static int max1 = 5;

  @RepeatRule.Repeat( times = 4 )
  @Test public void testRandomNumber_LessThan() {
    int num = RandomUtil.getRandomNumber(min1, max1);
    assertTrue("Random number is less than boundry: " + num, min1 <= num);
  }

  @RepeatRule.Repeat( times = 4 )
  @Test public void testRandomNumber_GreaterThan() {
    int num = RandomUtil.getRandomNumber(min1, max1);
    assertTrue("Random number is greater than boundry: " + num, max1 >= num);
  }


}
