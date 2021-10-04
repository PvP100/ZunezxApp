package com.example.zunezxapp.adapter;


import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseAdapter;
import com.example.zunezxapp.databinding.ConfirmOrderItemBinding;
import com.example.zunezxapp.entity.Product;

public class ConfirmAdapter extends BaseAdapter<ConfirmOrderItemBinding> {
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
        ((ConfirmViewHolder) holder).bind(null);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ConfirmViewHolder extends BaseViewHolder<Product> {

        ConfirmOrderItemBinding binding;

        public ConfirmViewHolder(ConfirmOrderItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        protected void bind(Product data) {

        }
    }
}
