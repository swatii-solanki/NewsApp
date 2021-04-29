package com.newsapp.ui.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.newsapp.R;
import com.newsapp.databinding.ActivityMainBinding;
import com.newsapp.model.MResponse;
import com.newsapp.ui.adapter.ViewPagerAdapter;
import com.newsapp.ui.viewmodel.MainActivityViewModel;
import com.newsapp.utils.Utility;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    private void init() {
        initializeViewModel();
        if (Utility.isNetworkAvailable(this))
            getSources();
        else Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show();
    }

    private void initializeViewModel() {
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
    }

    private void getSources() {
        viewModel.sourceLiveData().observe(this, this::setUpViewPager);
    }

    private void setUpViewPager(MResponse response) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, response.getSourceList());
        binding.viewpager.setAdapter(viewPagerAdapter);
        binding.viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                viewPagerAdapter.createFragment(position);
                viewPagerAdapter.notifyDataSetChanged();
            }
        });
        new TabLayoutMediator(binding.tabLayout, binding.viewpager, (tab, position) -> tab.setText(response.getSourceList().get(position).getName())).attach();
    }
}