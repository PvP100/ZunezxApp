package com.example.zunezxapp.ui.register;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentRegisterBinding;
import com.example.zunezxapp.ui.home.HomeFragment;
import com.example.zunezxapp.ui.login.LoginFragment;
import com.example.zunezxapp.ui.main.MainFragment;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class RegisterFragment extends BaseFragment<RegisterViewModel, FragmentRegisterBinding> implements View.OnClickListener {



    @Override
    protected RegisterViewModel creatViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(RegisterViewModel.class);
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
        viewModel.getStatus().observe(this, it -> {
            if (it) {
                Toast.makeText(requireContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                getVC().replaceFragment(LoginFragment.class, null);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.edtBirthdayRegis.setOnClickListener(this);
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
            int gender = 0;
            if(binding.spinnerGenderRegis.getSelectedItem().toString().equals("Nam")) {
                gender = 1;
            }
            viewModel.register(
                    binding.edtPasswordRegis.getText().toString().trim(),
                    binding.edtAddressRegis.getText().toString().trim(),
                    binding.edtBirthdayRegis.getText().toString().trim(),
                    binding.edtEmailRegis.getText().toString().trim(),
                    binding.edtHoRegis.getText().toString().trim() + " " + binding.edtTenRegis.getText().toString().trim(),
                    gender, binding.edtPhoneRegis.getText().toString().trim()
            );
        } else if (view == binding.tvDangNhapRegis) {
            getVC().backFromAddFragment(null);
        } else if (view == binding.edtBirthdayRegis) {
            datePicker.pickDate(binding.edtBirthdayRegis);
        }
    }
}
