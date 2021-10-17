package com.example.zunezxapp.adapter;

import com.bumptech.glide.Glide;
import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseAdapter;
import com.example.zunezxapp.databinding.CategoryItemBinding;
import com.example.zunezxapp.entity.HomeProduct;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends BaseAdapter<CategoryItemBinding> {

    List<HomeProduct> list = new ArrayList<>();

    DecimalFormat format = new DecimalFormat("###,###,###");

    public void setSearch(List<HomeProduct> list) {
        this.list = list;
        notifyDataSetChanged();
    }

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
        ((CategoryDetailViewHolder) holder).bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CategoryDetailViewHolder extends BaseViewHolder<HomeProduct> {

        CategoryItemBinding binding;

        public CategoryDetailViewHolder(CategoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        protected void bind(HomeProduct data) {
            Glide.with(binding.getRoot()).load(data.getLogoUrl()).into(binding.imgSearch);
            binding.tvProductNameCategoryItem.setText(data.getName());
            binding.tvPriceSearch.setText(format.format(((int) data.getPrice())) + "đ");
        }
    }
}
