package com.example.zunezxapp.ui.categorydetail;

import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.zunezxapp.R;
import com.example.zunezxapp.adapter.SearchAdapter;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.custom.GridSpacingItemDecoration;
import com.example.zunezxapp.databinding.FragmentSearchBinding;

public class SearchFragment extends BaseFragment<SearchViewModel, FragmentSearchBinding> implements View.OnClickListener {
    @Override
    protected SearchViewModel creatViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(SearchViewModel.class);
    }

    @Override
    protected void backFromAddFragment() {
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView() {
        viewModel.search(getArguments().getString("search"));
        SearchAdapter searchAdapter = new SearchAdapter();
        viewModel.getListHomeProduct().observe(this, it -> {
            if (it.size() > 0) {
                searchAdapter.setSearch(it);
                binding.layoutSearch.setVisibility(View.INVISIBLE);
            } else {
                binding.layoutSearch.setVisibility(View.VISIBLE);
            }
        });
        binding.rcvCategory.setAdapter(searchAdapter);
        binding.rcvCategory.addItemDecoration(new GridSpacingItemDecoration(2, 50, true));
        binding.rcvCategory.setLayoutManager(new GridLayoutManager(requireContext(), 2));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.toolbarCategory.setNavigationOnClickListener(this);
    }

    @Override
    protected boolean backPressed() {
        getVC().backFromAddFragment(null);
        return false;
    }

    @Override
    public void onClick(View view) {
        getVC().backFromAddFragment(null);
    }
}
