package com.example.zunezxapp.ui.profile;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentProfileBinding;
import com.example.zunezxapp.ui.changepassword.ChangePasswordFragment;
import com.example.zunezxapp.ui.login.LoginFragment;
import com.example.zunezxapp.ui.login.LoginViewModel;

public class ProfileFragment extends BaseFragment<ProfileViewModel, FragmentProfileBinding> implements View.OnClickListener {
    @Override
    protected ProfileViewModel creatViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(ProfileViewModel.class);
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
        binding.spinnerGenderProfile.setEnabled(false);
        viewModel.getProfile();
        viewModel.getProfileMutableLiveData().observe(this, it -> {
            if (it != null) {
                binding.edtBirthdayUserProfile.setText(it.getBirthday());
                binding.edtAddressUserProfile.setText(it.getAddress());
                binding.edtEmailUserProfile.setText(it.getEmail());
                binding.edtPhoneUserProfile.setText(it.getPhone());
                if (it.getGender() == 0) {
                    binding.spinnerGenderProfile.setSelection(1);
                } else {
                    binding.spinnerGenderProfile.setSelection(0);
                }
                binding.edtNameProfile.setText(it.getFullName());
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.icUpdate.setOnClickListener(this);
        binding.edtBirthdayUserProfile.setOnClickListener(this);
        binding.btnChangePasswordProfile.setOnClickListener(this);
        binding.btnChinhSuaHoSo.setOnClickListener(this);
        binding.icCancel.setOnClickListener(this);
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
            viewModel.logout();
            getVC().replaceFragment(LoginFragment.class, null);
        } else if (view == binding.icCancel) {
            viewModel.getProfile();
            binding.edtNameProfile.setEnabled(true);
            binding.icLogout.setVisibility(View.VISIBLE);
            binding.icCancel.setVisibility(View.INVISIBLE);
            binding.icUpdate.setVisibility(View.INVISIBLE);
            binding.edtEmailUserProfile.setEnabled(false);
            binding.edtAddressUserProfile.setEnabled(false);
            binding.edtBirthdayUserProfile.setEnabled(false);
            binding.spinnerGenderProfile.setEnabled(false);
            binding.edtPhoneUserProfile.setEnabled(false);
        } else if (view == binding.btnChinhSuaHoSo) {
            binding.edtNameProfile.setEnabled(true);
            binding.icLogout.setVisibility(View.GONE);
            binding.icCancel.setVisibility(View.VISIBLE);
            binding.icUpdate.setVisibility(View.VISIBLE);
            binding.edtEmailUserProfile.setEnabled(true);
            binding.edtAddressUserProfile.setEnabled(true);
            binding.edtBirthdayUserProfile.setEnabled(true);
            binding.spinnerGenderProfile.setEnabled(true);
            binding.edtPhoneUserProfile.setEnabled(true);
        } else if (view == binding.edtBirthdayUserProfile) {
            datePicker.pickDate(binding.edtBirthdayUserProfile);
        } else if (view == binding.icUpdate) {
            int gender = 0;
            if (binding.spinnerGenderProfile.getSelectedItem().toString().equals("Nam")) {
                gender = 1;
            }
            viewModel.updateProfile(
                    binding.edtAddressUserProfile.getText().toString().trim(),
                    binding.edtBirthdayUserProfile.getText().toString().trim(),
                    binding.edtEmailUserProfile.getText().toString().trim(),
                    binding.edtNameProfile.getText().toString().trim(),
                    gender,
                    binding.edtPhoneUserProfile.getText().toString().trim()
            );
        }
    }
}
