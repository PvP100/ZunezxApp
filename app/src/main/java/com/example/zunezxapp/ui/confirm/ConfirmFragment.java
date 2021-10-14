package com.example.zunezxapp.ui.confirm;

import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.zunezxapp.R;
import com.example.zunezxapp.adapter.ConfirmAdapter;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentConfirmBinding;
import com.example.zunezxapp.entity.Cart;
import com.example.zunezxapp.ui.main.MainFragment;

import java.text.DecimalFormat;

public class ConfirmFragment extends BaseFragment<ConfirmViewModel, FragmentConfirmBinding> implements View.OnClickListener {

    private ConfirmAdapter confirmAdapter;

    private DecimalFormat format;

    @Override
    protected ConfirmViewModel creatViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(ConfirmViewModel.class);
    }

    @Override
    protected void backFromAddFragment() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_confirm;
    }

    @Override
    protected void initView() {
        format = new DecimalFormat("###,###,###");
        viewModel.getInfo();
        viewModel.getCart();
        confirmAdapter = new ConfirmAdapter();
        binding.rcvConfirm.setAdapter(confirmAdapter);
        binding.rcvConfirm.setLayoutManager(new LinearLayoutManager(requireContext()));
        viewModel.getListCartLiveDataConfirm().observe(this, it -> {
            confirmAdapter.setListOrderConfirm(it);
            int tong = 0;
            for (Cart cart : it) {
                tong += cart.getPrice() * cart.getQuantity();
                binding.tvTotal.setText(format.format(tong) + "đ");
            }
        });
        viewModel.getProfileMutableLiveData().observe(this, it -> {
            binding.tvCustomerNameConfirm.setText(it.getFullName());
            binding.tvAddressDetail.setText(it.getAddress());
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.btnDatHangConfirm.setOnClickListener(this);
        binding.toolbarConfirm.setNavigationOnClickListener(this);
    }

    @Override
    protected boolean backPressed() {
        getVC().backFromAddFragment(null);
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnDatHangConfirm) {
            getVC().replaceFragment(MainFragment.class, null);
        } else {
            getVC().backFromAddFragment(null);
        }
    }
}
