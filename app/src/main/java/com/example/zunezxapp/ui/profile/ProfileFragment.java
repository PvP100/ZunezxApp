package com.example.zunezxapp.ui.profile;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentProfileBinding;

public class ProfileFragment extends BaseFragment<ProfileViewModel, FragmentProfileBinding> {
    @Override
    protected ProfileViewModel creatViewModel() {
        return null;
    }

    @Override
    protected void backFromAddFragment() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_profile;
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
