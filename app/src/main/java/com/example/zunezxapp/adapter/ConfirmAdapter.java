package com.example.zunezxapp.adapter;


import com.bumptech.glide.Glide;
import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseAdapter;
import com.example.zunezxapp.databinding.ConfirmOrderItemBinding;
import com.example.zunezxapp.entity.Cart;
import com.example.zunezxapp.entity.HomeProduct;
import com.example.zunezxapp.entity.Order;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ConfirmAdapter extends BaseAdapter<ConfirmOrderItemBinding> {

    List<Cart> listOrderConfirm = new ArrayList<>();

    DecimalFormat format = new DecimalFormat("###,###,###");

    public void setListOrderConfirm(List<Cart> list) {
        listOrderConfirm = list;
        notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.confirm_order_item;
    }

    @Override
    protected BaseViewHolder solvedOnCreateViewHolder(ConfirmOrderItemBinding binding) {
        return new ConfirmViewHolder(binding);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {
        ((ConfirmViewHolder) holder).bind(listOrderConfirm.get(position));
    }

    @Override
    public int getItemCount() {
        return listOrderConfirm.size();
    }

    class ConfirmViewHolder extends BaseViewHolder<Cart> {

        ConfirmOrderItemBinding binding;

        public ConfirmViewHolder(ConfirmOrderItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        protected void bind(Cart data) {
            binding.tvCountConfirm.setText("x" + data.getQuantity());
            binding.tvProductNameConfirmItem.setText(data.getName());
            binding.tvSizeCart.setText(data.getSize());
            binding.tvPriceConfirm.setText(format.format(((int) data.getPrice())) + "đ");
            Glide.with(binding.getRoot()).load(data.getAvatarUrl()).into(binding.imgProductConfirm);
        }
    }
}
