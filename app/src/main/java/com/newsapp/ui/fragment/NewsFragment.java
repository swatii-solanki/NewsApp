package com.newsapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.newsapp.R;
import com.newsapp.databinding.FragmentNewsBinding;
import com.newsapp.model.MArticle;
import com.newsapp.retrofit.API;
import com.newsapp.ui.adapter.ArticleAdapter;
import com.newsapp.ui.viewmodel.NewsViewModel;
import com.newsapp.utils.MyLoader;
import com.newsapp.utils.Utility;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NewsFragment extends Fragment {

    private static final String TAG = "NewsFragment";
    private FragmentNewsBinding binding;
    private NewsViewModel viewModel;
    private MyLoader loader;
    private String id;

    public static NewsFragment newInstance(String id) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(API.ID, id);
        newsFragment.setArguments(args);
        return newsFragment;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(API.ID))
            id = getArguments().getString(API.ID);
        loader = new MyLoader(requireActivity());
        init();
    }

    private void init() {
        initializeViewModel();
        if (Utility.isNetworkAvailable(requireContext()))
            getArticles();
        else Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
    }

    private void initializeViewModel() {
        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);
    }

    public void getArticles() {
        viewModel.articleLiveData(id, loader).observe(this, response -> {
            if (response != null && response.getArticles() != null && response.getArticles().size() > 0) {
                binding.tvNoData.setVisibility(View.GONE);
                binding.rv.setVisibility(View.VISIBLE);
                setRecyclerView(response.getArticles());
            } else {
                binding.tvNoData.setVisibility(View.VISIBLE);
                binding.rv.setVisibility(View.GONE);
            }
        });
    }

    private void setRecyclerView(List<MArticle> articles) {
        binding.rv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        ArticleAdapter articleAdapter = new ArticleAdapter();
        binding.rv.setAdapter(articleAdapter);
        articleAdapter.setArticles(articles);
    }
}