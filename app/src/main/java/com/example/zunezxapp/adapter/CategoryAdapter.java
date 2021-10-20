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
    private CategoryOnClickListener onClick;

    public void setHomeCategoryList(List<HomeCategory> homeCategoryList) {
        this.homeCategoryList = homeCategoryList;
        notifyDataSetChanged();
    }

    public void setOnClick(CategoryOnClickListener onClick) {
        this.onClick = onClick;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.category_title_item;
    }

    @Override
    protected BaseViewHolder solvedOnCreateViewHolder(CategoryTitleItemBinding binding) {
        return new HomeCateViewHolder(binding, onClick);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {
        ((HomeCateViewHolder) holder).bind(homeCategoryList.get(position));
    }

    @Override
    public int getItemCount() {
        return homeCategoryList.size();
    }

    class HomeCateViewHolder extends BaseViewHolder<HomeCategory> implements View.OnClickListener {

        CategoryTitleItemBinding binding;
        private CategoryOnClickListener onClick;

        public HomeCateViewHolder(CategoryTitleItemBinding binding, CategoryOnClickListener onClick) {
            super(binding.getRoot());
            this.binding = binding;
            this.onClick = onClick;
        }

        @Override
        protected void bind(HomeCategory data) {
            binding.tvCategoryTitle.setText(data.getTitle());
            binding.tvCategoryTitle.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClick.onCateClick(homeCategoryList.get(getAdapterPosition()).getId());
        }
    }

    public interface CategoryOnClickListener {
        void onCateClick(String cateId);
    }
}
