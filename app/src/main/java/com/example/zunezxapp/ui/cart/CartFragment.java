package com.example.zunezxapp.ui.cart;

import android.view.View;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.zunezxapp.R;
import com.example.zunezxapp.adapter.CartAdapter;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentCartBinding;
import com.example.zunezxapp.entity.Cart;
import com.example.zunezxapp.ui.confirm.ConfirmFragment;

import java.text.DecimalFormat;

public class CartFragment extends BaseFragment<CartViewModel, FragmentCartBinding> implements View.OnClickListener {

    private CartAdapter cartAdapter;

    private DecimalFormat format;

    @Override
    protected CartViewModel creatViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(CartViewModel.class);
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
        format = new DecimalFormat("###,###,###");
        viewModel.getCart();
        cartAdapter = new CartAdapter();
        binding.rcvCart.setAdapter(cartAdapter);
        binding.rcvCart.setLayoutManager(new LinearLayoutManager(requireContext()));
        viewModel.getListCartLiveData().observe(this, it -> {
            cartAdapter.setListCart(it);
            int tong = 0;
            for (Cart cart : it) {
                tong += cart.getPrice();
            }
            binding.tvTongPriceCart.setText(format.format(tong) + "đ");
        });
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
