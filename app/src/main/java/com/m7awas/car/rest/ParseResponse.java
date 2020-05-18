package com.m7awas.car.rest;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.m7awas.car.Car;

import java.util.ArrayList;
import java.util.List;

public class ParseResponse {

    public static List<Car> parseCars(JsonObject mainObj) {
        List<Car> cars = new ArrayList<>();

        JsonArray arr = mainObj.get("data").getAsJsonArray();
        for (int i = 0; i < arr.size(); i++)
        {
            JsonObject carObj = arr.get(i).getAsJsonObject();

            cars.add(new Car(carObj.get("id").getAsInt()
                    , carObj.get("imageUrl").getAsString()
                    , carObj.get("brand").getAsString()
                    , carObj.get("constructionYear").getAsString()
                    , carObj.get("isUsed").getAsBoolean()));
        }

        return cars;
    }
}
