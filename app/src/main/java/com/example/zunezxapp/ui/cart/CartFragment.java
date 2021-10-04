package com.example.zunezxapp.ui.cart;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.zunezxapp.R;
import com.example.zunezxapp.adapter.CartAdapter;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentCartBinding;
import com.example.zunezxapp.ui.confirm.ConfirmFragment;

public class CartFragment extends BaseFragment<CartViewModel, FragmentCartBinding> implements View.OnClickListener {

    private CartAdapter cartAdapter;

    @Override
    protected CartViewModel creatViewModel() {
        return null;
    }

    @Override
    protected void backFromAddFragment() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_cart;
    }

    @Override
    protected void initView() {
        cartAdapter = new CartAdapter();
        binding.rcvCart.setAdapter(cartAdapter);
        binding.rcvCart.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.btnMuaHangCart.setOnClickListener(this);
        binding.toolbarCart.setNavigationOnClickListener(this);
    }

    @Override
    protected boolean backPressed() {
        getVC().backFromAddFragment(null);
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnMuaHangCart) {
            getVC().addFragment(ConfirmFragment.class, null, true, true);
        } else {
            getVC().backFromAddFragment(null);
        }
    }
}
