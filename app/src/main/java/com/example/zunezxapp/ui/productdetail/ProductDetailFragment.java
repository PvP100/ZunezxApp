package com.example.zunezxapp.ui.productdetail;

import android.view.View;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentProductDetailBinding;
import com.example.zunezxapp.ui.cart.CartFragment;

public class ProductDetailFragment extends BaseFragment<ProductDetailViewModel, FragmentProductDetailBinding> implements View.OnClickListener {
    @Override
    protected ProductDetailViewModel creatViewModel() {
        return null;
    }

    @Override
    protected void backFromAddFragment() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_product_detail;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.icBackProductDetail.setOnClickListener(this);
        binding.icCartProductDetail.setOnClickListener(this);
    }

    @Override
    protected boolean backPressed() {
        getVC().backFromAddFragment(null);
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view == binding.icBackProductDetail) {
            getVC().backFromAddFragment(null);
        } else if (view == binding.icCartProductDetail) {
            getVC().addFragment(CartFragment.class, null, true, true);
        }
    }
}
