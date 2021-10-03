package com.example.zunezxapp.ui.register;

import android.view.View;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentRegisterBinding;
import com.example.zunezxapp.ui.home.HomeFragment;
import com.example.zunezxapp.ui.main.MainFragment;

public class RegisterFragment extends BaseFragment<RegisterViewModel, FragmentRegisterBinding> implements View.OnClickListener {
    @Override
    protected RegisterViewModel creatViewModel() {
        return null;
    }

    @Override
    protected void backFromAddFragment() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.btnRegister.setOnClickListener(this);
        binding.tvDangNhapRegis.setOnClickListener(this);
    }

    @Override
    protected boolean backPressed() {
        getVC().backFromAddFragment(null);
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnRegister) {
            getVC().addFragment(MainFragment.class, null, true, false);
        } else if (view == binding.tvDangNhapRegis) {
            getVC().backFromAddFragment(null);
        }
    }
}
