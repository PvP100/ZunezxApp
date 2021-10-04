package com.example.zunezxapp.ui.confirm;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.zunezxapp.R;
import com.example.zunezxapp.adapter.ConfirmAdapter;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentConfirmBinding;
import com.example.zunezxapp.ui.main.MainFragment;

public class ConfirmFragment extends BaseFragment<ConfirmViewModel, FragmentConfirmBinding> implements View.OnClickListener {

    private ConfirmAdapter confirmAdapter;

    @Override
    protected ConfirmViewModel creatViewModel() {
        return null;
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
        confirmAdapter = new ConfirmAdapter();
        binding.rcvConfirm.setAdapter(confirmAdapter);
        binding.rcvConfirm.setLayoutManager(new LinearLayoutManager(requireContext()));
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
