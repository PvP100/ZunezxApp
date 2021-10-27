package com.example.zunezxapp.ui.order;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.zunezxapp.R;
import com.example.zunezxapp.adapter.OrderAdapter;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentOrderBinding;
import com.example.zunezxapp.ui.orderdetail.OrderDetailFragment;

public class OrderFragment extends BaseFragment<OrderViewModel, FragmentOrderBinding> implements SwipeRefreshLayout.OnRefreshListener, OrderAdapter.OnClickDetailOrder {

    private OrderAdapter orderAdapter;

    @Override
    protected OrderViewModel creatViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(OrderViewModel.class);
    }

    @Override
    protected void backFromAddFragment() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_order;
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
        orderAdapter = new OrderAdapter();
        viewModel.getCustomerOrder();
        viewModel.getOrderLiveData().observe(this, it -> {
            orderAdapter.setListOrder(it.getData().getResult(), this);
            binding.swipeToRefreshOrder.setRefreshing(false);
        });
        binding.rcvHistory.setAdapter(orderAdapter);
        binding.rcvHistory.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.swipeToRefreshOrder.setOnRefreshListener(this);
    }

    @Override
    protected boolean backPressed() {
        return false;
    }

    @Override
    public void onRefresh() {
        viewModel.getCustomerOrder();
    }

    @Override
    public void onClickOrder(String orderId) {
        Bundle bundle = new Bundle();
        bundle.putString("id", orderId);
        getVC().addFragment(OrderDetailFragment.class, bundle, true, true);
    }
}
