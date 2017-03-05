package net.gideonbros.dailymeal.presentation.presenter;

import net.gideonbros.dailymeal.presentation.view.IDailyMealView;

/**
 * Created by Matija on 3.3.2017..
 */

public interface IDailyMealPresenter {
    void setView(IDailyMealView view);
    void startCollectingData(Double latitude, Double longitude);
    void filterData(String searchString);
}
