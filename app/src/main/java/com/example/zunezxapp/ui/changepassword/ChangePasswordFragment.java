package com.example.zunezxapp.ui.changepassword;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentChangePasswordBinding;

public class ChangePasswordFragment extends BaseFragment<ChangePasswordViewmodel, FragmentChangePasswordBinding> {
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

    }

    @Override
    protected boolean backPressed() {
        getVC().backFromAddFragment(null);
        return false;
    }
}
