package net.gideonbros.dailymeal.service;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import net.gideonbros.dailymeal.data.models.DailyMealModel;

import java.io.IOException;

/**
 * Created by Matija on 3.3.2017..
 */

public class JsonSerializationAdapter extends TypeAdapter<DailyMealModel> {

    @Override
    public void write(JsonWriter out, DailyMealModel value) throws IOException {
    }

    @Override
    public DailyMealModel read(JsonReader in) throws IOException {
        return null;
    }
}
