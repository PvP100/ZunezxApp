package com.example.zunezxapp.adapter;

import android.view.View;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseAdapter;
import com.example.zunezxapp.databinding.CategoryTitleItemBinding;
import com.example.zunezxapp.entity.HomeCategory;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends BaseAdapter<CategoryTitleItemBinding> {

    private List<HomeCategory> homeCategoryList = new ArrayList<>();

    public void setHomeCategoryList(List<HomeCategory> homeCategoryList) {
        this.homeCategoryList = homeCategoryList;
        notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.category_title_item;
    }

    @Override
    protected BaseViewHolder solvedOnCreateViewHolder(CategoryTitleItemBinding binding) {
        return new HomeCateViewHolder(binding);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {
        ((HomeCateViewHolder) holder).bind(homeCategoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return homeCategoryList.size();
    }

    class HomeCateViewHolder extends BaseViewHolder<HomeCategory> {

        CategoryTitleItemBinding binding;

        public HomeCateViewHolder(CategoryTitleItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        protected void bind(HomeCategory data) {
            binding.tvCategoryTitle.setText(data.getTitle());
        }
    }
}
