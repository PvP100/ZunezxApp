package com.example.zunezxapp.ui.changepassword;

import android.view.View;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentChangePasswordBinding;

public class ChangePasswordFragment extends BaseFragment<ChangePasswordViewmodel, FragmentChangePasswordBinding> implements View.OnClickListener {
    @Override
    protected ChangePasswordViewmodel creatViewModel() {
        return null;
    }

    @Override
    protected void backFromAddFragment() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_change_password;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.toolbarChangePass.setNavigationOnClickListener(this);
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
