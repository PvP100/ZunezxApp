package com.example.zunezxapp.adapter;

import android.content.Context;
import android.view.View;

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
    private OnGetAllCLickListener onClick;

    @Inject
    Context context;

    public HomeAdapter(HomeCategoryAdapter adapter, OnGetAllCLickListener onClick) {
        this.adapter = adapter;
        this.onClick = onClick;
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
        return new HomeViewHolder(binding, onClick);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {
        ((HomeViewHolder) holder).bind(null);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    private class HomeViewHolder extends BaseViewHolder<Home> implements View.OnClickListener {
        HomeFragmentItemBinding binding;
        OnGetAllCLickListener onGetAllCLickListener;

        public HomeViewHolder(HomeFragmentItemBinding binding, OnGetAllCLickListener onGetAllCLickListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.onGetAllCLickListener = onGetAllCLickListener;
        }

        @Override
        protected void bind(Home data) {
            if (data == null) {
//                binding.tvCategoryTitle.setText("dsfsdfsfds");
                binding.rcvItem.setAdapter(adapter);
                binding.rcvItem.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                binding.tvXemTatCa.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View view) {
            onGetAllCLickListener.onGetAllClick();
        }
    }

    public interface OnGetAllCLickListener {
        void onGetAllClick();
    }
}
