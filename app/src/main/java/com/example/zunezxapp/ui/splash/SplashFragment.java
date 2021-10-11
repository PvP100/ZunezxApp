package com.example.zunezxapp.ui.splash;

import android.os.Handler;

import androidx.lifecycle.ViewModelProvider;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.SplashScreenBinding;
import com.example.zunezxapp.ui.login.LoginFragment;
import com.example.zunezxapp.ui.main.MainFragment;

public class SplashFragment extends BaseFragment<SplashViewModel, SplashScreenBinding> {
    @Override
    protected SplashViewModel creatViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(SplashViewModel.class);
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
        viewModel.checkLoginStatus();
        viewModel.getLoginStatus().observe(this, it -> {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (it) {
                        getVC().replaceFragment(MainFragment.class, null);
                    } else {
                        getVC().replaceFragment(LoginFragment.class, null);
                    }
                }
            }, 2000);
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean backPressed() {
        return false;
    }
}
