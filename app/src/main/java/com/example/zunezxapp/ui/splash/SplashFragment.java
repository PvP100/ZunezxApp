package com.example.zunezxapp.ui.splash;

import android.os.Handler;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.SplashScreenBinding;
import com.example.zunezxapp.ui.login.LoginFragment;

public class SplashFragment extends BaseFragment<SplashViewModel, SplashScreenBinding> {
    @Override
    protected SplashViewModel creatViewModel() {
        return null;
    }

    @Override
    protected void backFromAddFragment() {

    }

    @Override
    protected int layoutId() {
        return R.layout.splash_screen;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getVC().replaceFragment(LoginFragment.class, null);
            }
        }, 2000);
    }

    @Override
    protected boolean backPressed() {
        return false;
    }
}
