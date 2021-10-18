package com.example.zunezxapp.ui.history;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.zunezxapp.R;
import com.example.zunezxapp.adapter.HistoryAdapter;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.base.BaseViewModel;
import com.example.zunezxapp.databinding.FragmentHistoryBinding;
import com.example.zunezxapp.ui.orderdetail.OrderDetailFragment;

public class HistoryFragment extends BaseFragment<HistoryViewModel, FragmentHistoryBinding> implements SwipeRefreshLayout.OnRefreshListener, HistoryAdapter.OnClickDetailOrder {

    private HistoryAdapter historyAdapter;

    @Override
    protected HistoryViewModel creatViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(HistoryViewModel.class);
    }

    @Override
    protected void backFromAddFragment() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_history;
    }

    @Override
    protected void initView() {
        historyAdapter = new HistoryAdapter();
        viewModel.getCustomerOrder();
        viewModel.getOrderLiveData().observe(this, it -> {
            historyAdapter.setListOrder(it.getData().getResult(), this);
            binding.swipeToRefreshOrder.setRefreshing(false);
        });
        binding.rcvHistory.setAdapter(historyAdapter);
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
