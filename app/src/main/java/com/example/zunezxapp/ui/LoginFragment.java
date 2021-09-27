package com.example.zunezxapp.ui;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentLoginBinding;

public class LoginFragment extends BaseFragment<LoginViewModel, FragmentLoginBinding> {
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

    }

    @Override
    protected boolean backPressed() {
        return false;
    }
}
