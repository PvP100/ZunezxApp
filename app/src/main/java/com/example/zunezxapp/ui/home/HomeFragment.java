package com.example.zunezxapp.ui.home;

import android.view.View;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.base.ViewController;
import com.example.zunezxapp.databinding.FragmentHomeBinding;
import com.example.zunezxapp.ui.cart.CartFragment;
import com.example.zunezxapp.ui.splash.SplashFragment;

public class HomeFragment extends BaseFragment<HomeViewModel, FragmentHomeBinding> implements View.OnClickListener {

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
        getVC().backFromAddFragment(null);
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view == binding.icCartHome) {
            getVC().addFragment(CartFragment.class, null, true, true);
        }
    }
}
