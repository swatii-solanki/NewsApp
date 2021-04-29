package com.newsapp.repo;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.newsapp.model.MArticle;
import com.newsapp.model.MResponse;
import com.newsapp.retrofit.API;
import com.newsapp.retrofit.ApiClient;
import com.newsapp.utils.Constant;
import com.newsapp.utils.MyLoader;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleRepo {

    private final MutableLiveData<MResponse> mutableLiveData = new MutableLiveData<>();
    private List<MArticle> articleList = new ArrayList<>();

    private static ArticleRepo articleRepo = null;

    public static ArticleRepo getArticleRepo() {
        if (articleRepo == null)
            articleRepo = new ArticleRepo();
        return articleRepo;
    }

    public MutableLiveData<MResponse> getArticles(String sources, MyLoader loader) {
        loader.show();
        HashMap<String, Object> map = new HashMap<>();
        map.put(API.API_KEY, Constant.API_KEY);
        map.put(API.SOURCES, sources);
        map.put(API.PAGE, 1);
        Call<JsonElement> call = ApiClient.getRetrofitService().getArticles(map);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(@NotNull Call<JsonElement> call, @NotNull Response<JsonElement> response) {
                loader.dismiss();
                MResponse mResponse = new MResponse();
                if (response.isSuccessful()) {
                    articleList.clear();
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().toString());
                        Type type = new TypeToken<ArrayList<MArticle>>() {
                        }.getType();
                        articleList = new Gson().fromJson(jsonObject.getJSONArray(API.ARTICLES).toString(), type);
                        mResponse.setArticles(articleList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mutableLiveData.setValue(mResponse);
            }

            @Override
            public void onFailure(@NotNull Call<JsonElement> call, @NotNull Throwable t) {
                loader.dismiss();
            }
        });
        return mutableLiveData;
    }
}
