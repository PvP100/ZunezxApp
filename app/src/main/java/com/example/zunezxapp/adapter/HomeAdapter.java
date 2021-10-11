package com.example.zunezxapp.adapter;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseAdapter;
import com.example.zunezxapp.databinding.ProductHomeItemBinding;
import com.example.zunezxapp.entity.Home;
import com.example.zunezxapp.entity.HomeProduct;
import com.example.zunezxapp.entity.SubCategory;
import com.example.zunezxapp.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

public class HomeAdapter extends BaseAdapter<ProductHomeItemBinding> {

    private List<HomeProduct> listCate = new ArrayList<>();
    private OnGetAllCLickListener onClick;

    @Inject
    Context context;

    public HomeAdapter(OnGetAllCLickListener onClick) {
        this.onClick = onClick;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.product_home_item;
    }

    public void setHome(List<HomeProduct> listCate) {
        this.listCate = listCate;
        notifyDataSetChanged();
    }

    @Override
    protected BaseViewHolder solvedOnCreateViewHolder(ProductHomeItemBinding binding) {
        return new HomeViewHolder(binding, onClick);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {
        ((HomeViewHolder) holder).bind(listCate.get(position));
    }

    @Override
    public int getItemCount() {
        return listCate.size();
    }

    private class HomeViewHolder extends BaseViewHolder<HomeProduct> implements View.OnClickListener {
        ProductHomeItemBinding binding;
        OnGetAllCLickListener onGetAllCLickListener;

        public HomeViewHolder(ProductHomeItemBinding binding, OnGetAllCLickListener onGetAllCLickListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.onGetAllCLickListener = onGetAllCLickListener;
        }

        @Override
        protected void bind(HomeProduct data) {
            binding.getRoot().setOnClickListener(this);
            binding.tvProductNameHomeItem.setText(data.getName());
            Glide.with(binding.getRoot()).load(data.getLogoUrl()).error(R.drawable.ic_launcher_background).into(binding.imgProductHomeItem);
        }

        @Override
        public void onClick(View view) {
            onGetAllCLickListener.onGetAllClick(listCate.get(getAdapterPosition()).getId());
        }
    }

    public interface OnGetAllCLickListener {
        void onGetAllClick(String productId);
    }
}
