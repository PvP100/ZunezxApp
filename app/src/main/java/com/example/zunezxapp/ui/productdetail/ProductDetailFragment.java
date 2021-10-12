package com.example.zunezxapp.ui.productdetail;

import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentProductDetailBinding;
import com.example.zunezxapp.ui.cart.CartFragment;

public class ProductDetailFragment extends BaseFragment<ProductDetailViewModel, FragmentProductDetailBinding> implements View.OnClickListener {

    @Override
    protected ProductDetailViewModel creatViewModel() {
        return new ViewModelProvider(this, viewModelFactory).get(ProductDetailViewModel.class);
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
        if (getArguments().getString("productId") != null) {
            viewModel.getProductDetail(getArguments().getString("productId"));
        }
        viewModel.getProductDetailLive().observe(this, it -> {
            Glide.with(requireContext()).load(it.getCoverUrl()).into(binding.imgBackgroudnProductDetail);
            Glide.with(requireContext()).load(it.getAvatarUrl()).into(binding.imgAvaDetail);
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.icBackProductDetail.setOnClickListener(this);
        binding.icCartProductDetail.setOnClickListener(this);
        binding.btnThemVaoGioHang.setOnClickListener(this);
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
        } else if (view == binding.btnThemVaoGioHang) {
            viewModel.addToCart();
        }
    }
}
