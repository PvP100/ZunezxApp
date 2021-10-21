package com.example.zunezxapp.ui.productdetail;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentProductDetailBinding;
import com.example.zunezxapp.ui.cart.CartFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDetailFragment extends BaseFragment<ProductDetailViewModel, FragmentProductDetailBinding> implements View.OnClickListener {

    private DecimalFormat format;

    private ArrayAdapter<String> spinnerDetail;

    private List<String> listSize;
    
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
        format = new DecimalFormat("###,###,###");
        viewModel.getLoading().observe(this, it -> {
            if (it) {
                loadingDialog.show();
            } else {
                loadingDialog.hide();
            }
        });
        if (getArguments().getString("productId") != null) {
            viewModel.getProductDetail(getArguments().getString("productId"));
        }
        viewModel.getStatus().observe(this, it -> {
            if (true) {
                Toast.makeText(requireContext(), "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.getProductDetailLive().observe(this, it -> {
            binding.tvProductNameDetail.setText(it.getName());
            binding.tvProductDetailPrice.setText(format.format(it.getPrice()) + "đ");
            binding.tvProductType.setText(it.getDescription());
            String[] size = it.getSize().split(",");
            listSize = Arrays.asList(size);
            spinnerDetail = new ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,listSize);
            binding.spinnerSizeDetail.setAdapter(spinnerDetail);
            if (it.getQuantity() > 0) {
                binding.tvStatusDetail.setText("Còn hàng");
            } else {
                binding.tvStatusDetail.setText("Hết hàng");
            }
            Glide.with(requireContext()).load(it.getCoverUrl()).into(binding.imgBackgroudnProductDetail);
            Glide.with(requireContext()).load(it.getAvatarUrl()).into(binding.imgAvaDetail);
        });
        viewModel.getMessageError().observe(this, it -> {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show();
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
            viewModel.addToCart(binding.spinnerSizeDetail.getSelectedItem().toString());
        }
    }
}
