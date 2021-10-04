package com.example.zunezxapp.adapter;

import android.view.View;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseAdapter;
import com.example.zunezxapp.databinding.HistoryItemBinding;
import com.example.zunezxapp.entity.Order;

public class HistoryAdapter extends BaseAdapter<HistoryItemBinding> {
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
        ((HistoryViewHolder) holder).bind(null);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class HistoryViewHolder extends BaseViewHolder<Order> {

        HistoryItemBinding binding;

        public HistoryViewHolder(HistoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        protected void bind(Order data) {

        }
    }
}
