package com.example.zunezxapp.adapter;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseAdapter;
import com.example.zunezxapp.databinding.HomeFragmentItemBinding;
import com.example.zunezxapp.databinding.ProductHomeItemBinding;
import com.example.zunezxapp.entity.Home;
import com.example.zunezxapp.entity.HomeCategory;

public class HomeCategoryAdapter extends BaseAdapter<ProductHomeItemBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.product_home_item;
    }

    @Override
    protected BaseViewHolder solvedOnCreateViewHolder(ProductHomeItemBinding binding) {
        return new HomeCategoryViewHolder(binding);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    private class HomeCategoryViewHolder extends BaseViewHolder<HomeCategory> {
        ProductHomeItemBinding binding;

        public HomeCategoryViewHolder(ProductHomeItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        protected void bind(HomeCategory data) {
//            binding.tvCategoryTitle.setText("dsfsdfsfds");
        }
    }
}
