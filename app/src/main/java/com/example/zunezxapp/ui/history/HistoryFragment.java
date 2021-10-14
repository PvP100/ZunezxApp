package com.example.zunezxapp.ui.history;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.zunezxapp.R;
import com.example.zunezxapp.adapter.HistoryAdapter;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.base.BaseViewModel;
import com.example.zunezxapp.databinding.FragmentHistoryBinding;

public class HistoryFragment extends BaseFragment<HistoryViewModel, FragmentHistoryBinding> {

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
            historyAdapter.setListOrder(it.getData().getResult());
        });
        binding.rcvHistory.setAdapter(historyAdapter);
        binding.rcvHistory.setLayoutManager(new LinearLayoutManager(requireContext()));
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
