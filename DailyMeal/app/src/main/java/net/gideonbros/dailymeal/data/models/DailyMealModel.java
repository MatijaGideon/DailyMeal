package net.gideonbros.dailymeal.data.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Matija on 3.3.2017..
 */

public class DailyMealModel extends RealmObject {
    @PrimaryKey
    private int id;
}
