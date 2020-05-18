package com.m7awas.car.rest;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIs {

    @GET("cars")
    Call<JsonObject> getCars(@Query("page") int page);
}
