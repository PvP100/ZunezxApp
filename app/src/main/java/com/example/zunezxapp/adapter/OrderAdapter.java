package com.example.zunezxapp.adapter;

import android.graphics.Color;
import android.view.View;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseAdapter;
import com.example.zunezxapp.databinding.HistoryItemBinding;
import com.example.zunezxapp.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends BaseAdapter<HistoryItemBinding> {

    private List<Order> listOrder = new ArrayList<>();
    private OnClickDetailOrder onClickDetailOrder;

    public void setListOrder(List<Order> list, OnClickDetailOrder onClickDetailOrder) {
        listOrder = list;
        this.onClickDetailOrder = onClickDetailOrder;
        notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.history_item;
    }

    @Override
    protected BaseViewHolder solvedOnCreateViewHolder(HistoryItemBinding binding) {
        return new HistoryViewHolder(binding, onClickDetailOrder);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {
        ((HistoryViewHolder) holder).bind(listOrder.get(position));
    }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }

    class HistoryViewHolder extends BaseViewHolder<Order> implements View.OnClickListener {

        HistoryItemBinding binding;
        OnClickDetailOrder onClickDetailOrder;

        public HistoryViewHolder(HistoryItemBinding binding, OnClickDetailOrder onClickDetailOrder) {
            super(binding.getRoot());
            this.binding = binding;
            this.onClickDetailOrder = onClickDetailOrder;
        }

        @Override
        protected void bind(Order data) {
            binding.tvIdOrderItem.setText(data.getId());
            if (data.getIsCheck() == 0) {
                binding.tvNgayDatHang.setText("Đang xử lý");
                binding.tvNgayDatHang.setTextColor(Color.parseColor("#FFEC3D"));
            } else {
                binding.tvNgayDatHang.setText("Đơn hàng đã được xử lý");
                binding.tvNgayDatHang.setTextColor(Color.parseColor("#33FF00") );
            }
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClickDetailOrder.onClickOrder(listOrder.get(getAdapterPosition()).getId());
        }
    }

    public interface OnClickDetailOrder {
        void onClickOrder(String orderId);
    }
}
