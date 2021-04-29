package com.newsapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.newsapp.R;
import com.newsapp.databinding.ItemArticleBinding;
import com.newsapp.model.MArticle;
import com.newsapp.ui.activity.ViewArticleActivity;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private Context mContext;
    private List<MArticle> articles = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemArticleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_article, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MArticle article = articles.get(position);
        Glide.with(mContext).load(article.getUrlToImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.binding.iv);
        holder.binding.tvTitle.setText(article.getTitle());
        holder.binding.tvDescription.setText(article.getDescription());
        holder.binding.viewDetail.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ViewArticleActivity.class);
            intent.putExtra("url",article.getUrl());
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return articles != null ? articles.size() : 0;
    }

    public void setArticles(List<MArticle> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemArticleBinding binding;

        public ViewHolder(@NonNull ItemArticleBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
