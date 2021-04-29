package com.newsapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("sources")
    @Expose
    private List<MSource> sourceList;
    @SerializedName("articles")
    @Expose
    private List<MArticle> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MSource> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<MSource> sourceList) {
        this.sourceList = sourceList;
    }

    public List<MArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<MArticle> articles) {
        this.articles = articles;
    }
}
