package com.example.zunezxapp.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.zunezxapp.R;
import com.example.zunezxapp.adapter.CategoryAdapter;
import com.example.zunezxapp.adapter.HomeAdapter;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.custom.GridSpacingItemDecoration;
import com.example.zunezxapp.databinding.FragmentHomeBinding;
import com.example.zunezxapp.ui.cart.CartFragment;
import com.example.zunezxapp.ui.categorydetail.CategoryDetailFragment;
import com.example.zunezxapp.ui.productdetail.ProductDetailFragment;

public class HomeFragment extends BaseFragment<HomeViewModel, FragmentHomeBinding> implements View.OnClickListener, HomeAdapter.OnGetAllCLickListener, SwipeRefreshLayout.OnRefreshListener, CategoryAdapter.CategoryOnClickListener {

    private HomeAdapter homeAdapter;
    private CategoryAdapter categoryAdapter;

    @Override
    protected HomeViewModel creatViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(HomeViewModel.class);
    }

    @Override
    protected void backFromAddFragment() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        viewModel.getCategory();
        categoryAdapter = new CategoryAdapter();
        homeAdapter = new HomeAdapter(this);
        categoryAdapter.setOnClick(this);
        viewModel.getListHomeCate().observe(this, it -> {
            categoryAdapter.setHomeCategoryList(it);
            binding.swipeToRefreshHome.setRefreshing(false);
        });
        viewModel.getHomeProduct(0);
        viewModel.getListHomeProduct().observe(this, it -> {
            homeAdapter.setHome(it);
            binding.swipeToRefreshHome.setRefreshing(false);
        });
        binding.rcvAllHome.setAdapter(categoryAdapter);
        binding.rcvAllHome.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.rcvHome.setAdapter(homeAdapter);
        binding.rcvHome.addItemDecoration(new GridSpacingItemDecoration(2, 50, true));
        binding.rcvHome.setLayoutManager(new GridLayoutManager(requireContext(), 2));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.tvAllHome.setOnClickListener(this);
        binding.swipeToRefreshHome.setOnRefreshListener(this);
        binding.icCartHome.setOnClickListener(this);
    }

    @Override
    protected boolean backPressed() {
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view == binding.icCartHome) {
            getVC().addFragment(CartFragment.class, null, true, true);
        } else if (view == binding.tvAllHome) {
            viewModel.getHomeProduct(0);
        }
    }

    @Override
    public void onGetAllClick(String productId) {
        Bundle bundle = new Bundle();
        bundle.putString("productId", productId);
        getVC().addFragment(ProductDetailFragment.class, bundle, true, true);
    }

    @Override
    public void onRefresh() {
        viewModel.getHomeProduct(0);
        viewModel.getCategory();
        binding.swipeToRefreshHome.setRefreshing(false);
    }

    @Override
    public void onCateClick(int cateId) {
        viewModel.getHomeProduct(cateId);
    }
}
