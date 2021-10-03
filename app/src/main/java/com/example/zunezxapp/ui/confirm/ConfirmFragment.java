package com.example.zunezxapp.ui.confirm;

import android.view.View;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentConfirmBinding;

public class ConfirmFragment extends BaseFragment<ConfirmViewModel, FragmentConfirmBinding> implements View.OnClickListener {
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

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.btnDatHangConfirm.setOnClickListener(this);
    }

    @Override
    protected boolean backPressed() {
        getVC().backFromAddFragment(null);
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnDatHangConfirm) {

        }
    }
}
