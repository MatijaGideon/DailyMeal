package net.gideonbros.dailymeal.presentation.presenter;

import net.gideonbros.dailymeal.presentation.view.IRestaurantView;

/**
 * Created by Matija on 4.3.2017..
 */

public interface IRestaurantPresenter {
    void setView(IRestaurantView view, int dailyMealId);
}
