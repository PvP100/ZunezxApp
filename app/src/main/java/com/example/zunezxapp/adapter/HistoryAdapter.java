package com.example.zunezxapp.adapter;

import android.view.View;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseAdapter;
import com.example.zunezxapp.databinding.HistoryItemBinding;
import com.example.zunezxapp.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends BaseAdapter<HistoryItemBinding> {

    private List<Order> listOrder = new ArrayList<>();

    public void setListOrder(List<Order> list) {
        listOrder = list;
        notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.history_item;
    }

    @Override
    protected BaseViewHolder solvedOnCreateViewHolder(HistoryItemBinding binding) {
        return new HistoryViewHolder(binding);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {
        ((HistoryViewHolder) holder).bind(listOrder.get(position));
    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }

    class HistoryViewHolder extends BaseViewHolder<Order> {

        HistoryItemBinding binding;

        public HistoryViewHolder(HistoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        protected void bind(Order data) {
            binding.tvIdOrderItem.setText(data.getId());
            binding.tvNgayDatHang.setText(String.valueOf(data.getCreatedDate()));
        }
    }
}
