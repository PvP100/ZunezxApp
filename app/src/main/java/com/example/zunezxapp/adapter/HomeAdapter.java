package com.example.zunezxapp.adapter;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseAdapter;
import com.example.zunezxapp.databinding.HomeFragmentItemBinding;
import com.example.zunezxapp.entity.Home;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomeAdapter extends BaseAdapter<HomeFragmentItemBinding> {

    private List<Home> homes = new ArrayList<>();
    private HomeCategoryAdapter adapter;

    @Inject
    Context context;

    public HomeAdapter(HomeCategoryAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_fragment_item;
    }

    public void setHome(List<Home> homes) {
        this.homes = homes;
        notifyDataSetChanged();
    }

    @Override
    protected BaseViewHolder solvedOnCreateViewHolder(HomeFragmentItemBinding binding) {
        return new HomeViewHolder(binding);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {
        ((HomeViewHolder) holder).bind(null);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    private class HomeViewHolder extends BaseViewHolder<Home> {
        HomeFragmentItemBinding binding;

        public HomeViewHolder(HomeFragmentItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        protected void bind(Home data) {
            if (data == null) {
//                binding.tvCategoryTitle.setText("dsfsdfsfds");
                binding.rcvItem.setAdapter(adapter);
                binding.rcvItem.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            }
        }
    }
}
