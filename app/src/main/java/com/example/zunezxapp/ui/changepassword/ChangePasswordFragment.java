package com.example.zunezxapp.ui.changepassword;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentChangePasswordBinding;

public class ChangePasswordFragment extends BaseFragment<ChangePasswordViewmodel, FragmentChangePasswordBinding> implements View.OnClickListener {
    @Override
    protected ChangePasswordViewmodel creatViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(ChangePasswordViewmodel.class);
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
        viewModel.getStatus().observe(this, it -> {
            if (it) {
                Toast.makeText(requireActivity(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                getVC().backFromAddFragment(null);
            }
        });
        viewModel.getMessageError().observe(this, it -> {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.btnChangePassword.setOnClickListener(this);
        binding.toolbarChangePass.setNavigationOnClickListener(this);
    }

    @Override
    protected boolean backPressed() {
        getVC().backFromAddFragment(null);
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnChangePassword) {
            if (binding.edtNewPassword.getText().toString().trim().equals(binding.edtReNewPassword.getText().toString().trim())) {
                viewModel.changePassword(binding.edtOldPassword.getText().toString().trim(), binding.edtNewPassword.getText().toString().trim());
            } else {
                Toast.makeText(requireContext(), "Mật khẩu mới và mật khẩu nhập lại không khớp ", Toast.LENGTH_SHORT).show();
            }
        } else {
            getVC().backFromAddFragment(null);
        }
    }
}
