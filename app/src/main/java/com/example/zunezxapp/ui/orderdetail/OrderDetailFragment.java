package com.example.zunezxapp.ui.orderdetail;

import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.zunezxapp.R;
import com.example.zunezxapp.adapter.ConfirmAdapter;
import com.example.zunezxapp.adapter.OrderDetailAdapter;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentOrderDetailBinding;

public class OrderDetailFragment extends BaseFragment<OrderDetailViewModel, FragmentOrderDetailBinding> implements View.OnClickListener {

    private OrderDetailAdapter confirmAdapter;

    @Override
    protected OrderDetailViewModel creatViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(OrderDetailViewModel.class);
    }

    @Override
    protected void backFromAddFragment() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_order_detail;
    }

    @Override
    protected void initView() {
        confirmAdapter = new OrderDetailAdapter();
        if (getArguments() != null) {
            viewModel.getOrderDetail(getArguments().getString("id"));
        }

        binding.rcvConfirm.setAdapter(confirmAdapter);
        binding.rcvConfirm.setLayoutManager(new LinearLayoutManager(requireContext()));
        viewModel.getListOrder().observe(this, it -> {
            confirmAdapter.setListOrderConfirm(it);
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.toolbarConfirm.setNavigationOnClickListener(this);
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
