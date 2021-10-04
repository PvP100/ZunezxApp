package com.example.zunezxapp.adapter;

import android.view.View;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseAdapter;
import com.example.zunezxapp.databinding.CategoryItemBinding;
import com.example.zunezxapp.entity.Product;

public class CategoryDetailAdapter extends BaseAdapter<CategoryItemBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.category_item;
    }

    @Override
    protected BaseViewHolder solvedOnCreateViewHolder(CategoryItemBinding binding) {
        return new CategoryDetailViewHolder(binding);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {
        ((CategoryDetailViewHolder) holder).bind(null);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class CategoryDetailViewHolder extends BaseViewHolder<Product> {

        CategoryItemBinding binding;

        public CategoryDetailViewHolder(CategoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        protected void bind(Product data) {

        }
    }
}
