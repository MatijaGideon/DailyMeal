package net.gideonbros.dailymeal.util;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;

/**
 * Created by Matija on 7.3.2017..
 */

public class TimerUtil {
  //simulate data fetching
  private static final int min = 1;
  private static final int max = 4;

  public static Completable getCompletable() {
    Completable completable =
        Completable.timer(RandomUtil.getRandomNumber(min, max), TimeUnit.SECONDS);

    return completable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
  }
}
