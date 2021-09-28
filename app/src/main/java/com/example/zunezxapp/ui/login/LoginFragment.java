package com.example.zunezxapp.ui.login;

import android.view.View;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentLoginBinding;
import com.example.zunezxapp.ui.home.HomeFragment;
import com.example.zunezxapp.ui.register.RegisterFragment;

public class LoginFragment extends BaseFragment<LoginViewModel, FragmentLoginBinding> implements View.OnClickListener {
    @Override
    protected LoginViewModel creatViewModel() {
        return null;
    }

    @Override
    protected void backFromAddFragment() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.btnLogin.setOnClickListener(this);
        binding.tvDangKyLogin.setOnClickListener(this);
    }

    @Override
    protected boolean backPressed() {
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnLogin) {
            getVC().addFragment(HomeFragment.class, null, true, true);
        }
        if (view == binding.tvDangKyLogin) {
            getVC().addFragment(RegisterFragment.class, null, true, true);
        }
    }
}
