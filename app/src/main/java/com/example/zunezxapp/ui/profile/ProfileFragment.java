package com.example.zunezxapp.ui.profile;

import android.view.View;
import android.widget.Toast;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentProfileBinding;
import com.example.zunezxapp.ui.changepassword.ChangePasswordFragment;
import com.example.zunezxapp.ui.login.LoginFragment;

public class ProfileFragment extends BaseFragment<ProfileViewModel, FragmentProfileBinding> implements View.OnClickListener {
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
        binding.btnChangePasswordProfile.setOnClickListener(this);
        binding.icLogout.setOnClickListener(this);
    }

    @Override
    protected boolean backPressed() {
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnChangePasswordProfile) {
            getVC().addFragment(ChangePasswordFragment.class, null, true, true);
        } else if (view == binding.icLogout) {
            getVC().replaceFragment(LoginFragment.class, null);
        }
    }
}
