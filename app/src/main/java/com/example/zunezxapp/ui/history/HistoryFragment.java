package com.example.zunezxapp.ui.history;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.zunezxapp.R;
import com.example.zunezxapp.adapter.HistoryAdapter;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.base.BaseViewModel;
import com.example.zunezxapp.databinding.FragmentHistoryBinding;

public class HistoryFragment extends BaseFragment<BaseViewModel, FragmentHistoryBinding> {

    private HistoryAdapter historyAdapter;

    @Override
    protected BaseViewModel creatViewModel() {
        return null;
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
