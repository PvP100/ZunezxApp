package com.example.zunezxapp.ui.home;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.zunezxapp.R;
import com.example.zunezxapp.adapter.HomeAdapter;
import com.example.zunezxapp.adapter.HomeCategoryAdapter;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.base.ViewController;
import com.example.zunezxapp.databinding.FragmentHomeBinding;
import com.example.zunezxapp.ui.cart.CartFragment;
import com.example.zunezxapp.ui.splash.SplashFragment;

public class HomeFragment extends BaseFragment<HomeViewModel, FragmentHomeBinding> implements View.OnClickListener {

    private HomeAdapter homeAdapter;
    private HomeCategoryAdapter homeCategoryAdapter;

    @Override
    protected HomeViewModel creatViewModel() {
        return null;
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
        homeCategoryAdapter = new HomeCategoryAdapter();
        homeAdapter = new HomeAdapter(homeCategoryAdapter);
        binding.rcvHome.setAdapter(homeAdapter);
        binding.rcvHome.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.icCartHome.setOnClickListener(this);
    }

    @Override
    protected boolean backPressed() {
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view == binding.icCartHome) {
            getVC().addFragment(CartFragment.class, null, true, false);
        }
    }
}
