package com.example.zunezxapp;


import com.example.zunezxapp.base.BaseActivity;
import com.example.zunezxapp.ui.splash.SplashFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected int layoutId() {
        return R.id.container;
    }

    @Override
    protected void initView() {
        getViewController().addFragment(SplashFragment.class, null, false, false);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}