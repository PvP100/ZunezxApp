package com.example.zunezxapp.adapter;


import com.bumptech.glide.Glide;
import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseAdapter;
import com.example.zunezxapp.databinding.ConfirmOrderItemBinding;
import com.example.zunezxapp.entity.Cart;
import com.example.zunezxapp.entity.OrderDetail;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailAdapter extends BaseAdapter<ConfirmOrderItemBinding> {

    List<OrderDetail> listOrderDetail = new ArrayList<>();

    DecimalFormat format = new DecimalFormat("###,###,###");

    public void setListOrderConfirm(List<OrderDetail> list) {
        listOrderDetail = list;
        notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.confirm_order_item;
    }

    @Override
    protected BaseViewHolder solvedOnCreateViewHolder(ConfirmOrderItemBinding binding) {
        return new OrderViewHolder(binding);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {
        ((OrderViewHolder) holder).bind(listOrderDetail.get(position));
    }

    @Override
    public int getItemCount() {
        return listOrderDetail.size();
    }

    class OrderViewHolder extends BaseViewHolder<OrderDetail> {

        ConfirmOrderItemBinding binding;

        public OrderViewHolder(ConfirmOrderItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        protected void bind(OrderDetail data) {
            binding.tvCountConfirm.setText("x" + data.getQuantity());
            binding.tvProductNameConfirmItem.setText(data.getProductName());
            binding.tvSizeCart.setText(data.getSize());
            binding.tvPriceConfirm.setText(format.format(((int) data.getPrice())) + "đ");
            Glide.with(binding.getRoot()).load(data.getProductUrl()).into(binding.imgProductConfirm);
        }
    }
}
