package com.newsapp.ui.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.newsapp.model.MSource;
import com.newsapp.ui.fragment.NewsFragment;

import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final List<MSource> sourceList;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<MSource> sourceList) {
        super(fragmentActivity);
        this.sourceList = sourceList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return NewsFragment.newInstance(sourceList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return sourceList != null ? sourceList.size() : 0;
    }
}
