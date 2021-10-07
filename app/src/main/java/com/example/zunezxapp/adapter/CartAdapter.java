package com.example.zunezxapp.adapter;

import android.view.View;

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseAdapter;
import com.example.zunezxapp.databinding.CartItemBinding;
import com.example.zunezxapp.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends BaseAdapter<CartItemBinding> {

    private List<Product> list = new ArrayList<>();

    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public void setListCart(List<Product> list) {
        this.list = list;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.cart_item;
    }

    @Override
    protected BaseViewHolder solvedOnCreateViewHolder(CartItemBinding binding) {
        return new OrderViewHolder(binding);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {
        OrderViewHolder orderViewHolder = (OrderViewHolder) holder;
        viewBinderHelper.bind(orderViewHolder.binding.swipeToDelete, "343");
        ((OrderViewHolder) holder).bind(null);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class OrderViewHolder extends BaseViewHolder<Product> implements View.OnClickListener {

        CartItemBinding binding;
        int count = 1;

        public OrderViewHolder(CartItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        protected void bind(Product data) {
            binding.icMinusCartItem.setOnClickListener(this);
            binding.icPlusCartItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view == binding.icPlusCartItem) {
                count++;
                binding.tvCountProductCartItem.setText(String.valueOf(count));
            } else if (view == binding.icMinusCartItem) {
                if (count >= 2) {
                    count--;
                    binding.tvCountProductCartItem.setText(String.valueOf(count));
                }
            }
        }
    }
}
