package net.gideonbros.dailymeal.util;

import java.util.Random;

/**
 * Created by Matija on 3/6/2017.
 */

public class RandomUtil {
  public static int getRandomNumber(int min, int max) {
    Random random = new Random();
    return random.nextInt((max - min) + 1) + min;
  }
}
