package com.example.zunezxapp.ui.login;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentLoginBinding;
import com.example.zunezxapp.ui.main.MainFragment;
import com.example.zunezxapp.ui.register.RegisterFragment;

public class LoginFragment extends BaseFragment<LoginViewModel, FragmentLoginBinding> implements View.OnClickListener {

    @Override
    protected LoginViewModel creatViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(LoginViewModel.class);
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
        viewModel.getLoading().observe(this, it -> {
            if (it) {
                loadingDialog.show();
            } else {
                loadingDialog.hide();
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
            if (binding.tvUsername.getText().toString().trim().length() > 0 && binding.tvPassword.getText().toString().trim().length() > 0) {
                viewModel.userLogin(binding.edtUsername.getText().toString().trim(), binding.edtPassword.getText().toString().trim());
                viewModel.getMessage().observe(this, it -> {
                    if (it) {
                        getVC().replaceFragment(MainFragment.class, null);
                    }
                });
            } else {
                Toast.makeText(requireContext(), "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu!", Toast.LENGTH_SHORT).show();
            }
        }
        if (view == binding.tvDangKyLogin) {
            getVC().addFragment(RegisterFragment.class, null, true, true);
        }
    }
}
