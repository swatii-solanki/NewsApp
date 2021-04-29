package com.newsapp.retrofit;

import com.google.gson.JsonElement;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface RetrofitService {

    @GET(API.SOURCES)
    Call<JsonElement> getSources(@QueryMap HashMap<String, Object> map);

    @GET(API.EVERYTHING)
    Call<JsonElement> getArticles(@QueryMap HashMap<String, Object> map);

}


