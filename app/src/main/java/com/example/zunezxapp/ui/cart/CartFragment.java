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

import javax.inject.Inject;

import io.realm.Realm;

public class CartFragment extends BaseFragment<CartViewModel, FragmentCartBinding> implements View.OnClickListener, CartAdapter.OnDeleteProduct {

    @Inject
    Realm realm;

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
        viewModel.getLoading().observe(this, it -> {
            if (it) {
                loadingDialog.show();
            } else {
                loadingDialog.hide();
            }
        });
        format = new DecimalFormat("###,###,###");
        viewModel.getCart();
        cartAdapter = new CartAdapter();
        binding.rcvCart.setAdapter(cartAdapter);
        cartAdapter.setTotalCart(realm, viewModel, this);
        binding.rcvCart.setLayoutManager(new LinearLayoutManager(requireContext()));
        viewModel.getListCartLiveData().observe(this, it -> {
            cartAdapter.setListCart(it);
            if (it.size() > 0) {
                binding.btnMuaHangCart.setVisibility(View.VISIBLE);
                binding.tvTongTitleCart.setVisibility(View.VISIBLE);
                binding.tvTongPriceCart.setVisibility(View.VISIBLE);
                binding.layoutCheckCart.setVisibility(View.INVISIBLE);
                int tong = 0;
                for (Cart cart : it) {
                    tong += cart.getPrice() * cart.getQuantity();
                    binding.tvTongPriceCart.setText(format.format(tong) + "đ");
                }
            } else {
                binding.layoutCheckCart.setVisibility(View.VISIBLE);
                binding.btnMuaHangCart.setVisibility(View.INVISIBLE);
                binding.tvTongTitleCart.setVisibility(View.INVISIBLE);
                binding.tvTongPriceCart.setVisibility(View.INVISIBLE);
            }
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

    @Override
    public void onDelete(String id) {
        viewModel.deleteProductInCart(id);
        viewModel.onChangeCart();
    }
}
