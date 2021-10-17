package com.example.zunezxapp.adapter;

import android.view.View;

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

    private OnClickSearch onClickSearch;

    DecimalFormat format = new DecimalFormat("###,###,###");

    public void setSearch(List<HomeProduct> list, OnClickSearch onClickSearch) {
        this.list = list;
        this.onClickSearch = onClickSearch;
        notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.category_item;
    }

    @Override
    protected BaseViewHolder solvedOnCreateViewHolder(CategoryItemBinding binding) {
        return new CategoryDetailViewHolder(binding, onClickSearch);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {
        ((CategoryDetailViewHolder) holder).bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CategoryDetailViewHolder extends BaseViewHolder<HomeProduct> implements View.OnClickListener {

        CategoryItemBinding binding;
        OnClickSearch onClickSearch;

        public CategoryDetailViewHolder(CategoryItemBinding binding, OnClickSearch onClickSearch) {
            super(binding.getRoot());
            this.binding = binding;
            this.onClickSearch = onClickSearch;
        }

        @Override
        protected void bind(HomeProduct data) {
            Glide.with(binding.getRoot()).load(data.getLogoUrl()).into(binding.imgSearch);
            binding.tvProductNameCategoryItem.setText(data.getName());
            binding.tvPriceSearch.setText(format.format(((int) data.getPrice())) + "đ");
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClickSearch.onClickSearchProduct(list.get(getAdapterPosition()).getId());
        }
    }

    public interface OnClickSearch {
        void onClickSearchProduct(String id);
    }
}
