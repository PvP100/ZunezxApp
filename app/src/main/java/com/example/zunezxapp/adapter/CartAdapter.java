package com.example.zunezxapp.adapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseAdapter;
import com.example.zunezxapp.databinding.CartItemBinding;
import com.example.zunezxapp.entity.Cart;
import com.example.zunezxapp.ui.cart.CartViewModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class CartAdapter extends BaseAdapter<CartItemBinding> {

    private Realm realm;

    private OnDeleteProduct onDeleteProduct;

    private List<Cart> list = new ArrayList<>();

    CartViewModel cartViewModel;

    DecimalFormat format = new DecimalFormat("###,###,###");

    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public void setListCart(List<Cart> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setTotalCart(Realm realm, CartViewModel viewModel, OnDeleteProduct onDeleteProduct) {
        this.realm = realm;
        cartViewModel = viewModel;
        this.onDeleteProduct = onDeleteProduct;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.cart_item;
    }

    @Override
    protected BaseViewHolder solvedOnCreateViewHolder(CartItemBinding binding) {
        return new OrderViewHolder(binding, onDeleteProduct);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {
        OrderViewHolder orderViewHolder = (OrderViewHolder) holder;
        viewBinderHelper.setOpenOnlyOne(true);
        viewBinderHelper.bind(orderViewHolder.binding.swipeToDelete, list.get(position).getId());
        viewBinderHelper.closeLayout(list.get(position).getId());
        ((OrderViewHolder) holder).bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OrderViewHolder extends BaseViewHolder<Cart> implements View.OnClickListener {

        CartItemBinding binding;
        OnDeleteProduct onDeleteProduct;
        int count = 1;

        public OrderViewHolder(CartItemBinding binding, OnDeleteProduct onDeleteProduct) {
            super(binding.getRoot());
            this.binding = binding;
            this.onDeleteProduct = onDeleteProduct;
        }

        @Override
        protected void bind(Cart data) {
            count = data.getQuantity();
            Glide.with(binding.getRoot()).load(data.getAvatarUrl()).into(binding.imgProductCart);
            binding.tvProductNameCartItem.setText(data.getName());
            binding.tvSizeCart.setText(data.getSize());
            binding.tvPriceCart.setText(format.format(((int) data.getPrice()) * count) + "đ");
            binding.tvCountProductCartItem.setText(String.valueOf(data.getQuantity()));
            binding.icMinusCartItem.setOnClickListener(this);
            binding.icPlusCartItem.setOnClickListener(this);
            binding.tvDeleteCart.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view == binding.icPlusCartItem) {
                count++;
                binding.tvCountProductCartItem.setText(String.valueOf(count));
                double total = list.get(getAdapterPosition()).getPrice() * count;
                binding.tvPriceCart.setText(format.format(((int) total)) + "đ");
                realm.beginTransaction();
                list.get(getAdapterPosition()).setQuantity(count);
                realm.commitTransaction();
                cartViewModel.getCart();
            } else if (view == binding.icMinusCartItem) {
                if (count >= 2) {
                    count--;
                    binding.tvCountProductCartItem.setText(String.valueOf(count));
                    double total = list.get(getAdapterPosition()).getPrice() * count;
                    binding.tvPriceCart.setText(format.format(((int) total)) + "đ");
                    realm.beginTransaction();
                    list.get(getAdapterPosition()).setQuantity(count);
                    realm.commitTransaction();
                    cartViewModel.getCart();
                }
            } else if (view == binding.tvDeleteCart) {
                onDeleteProduct.onDelete(list.get(getAdapterPosition()).getId());
            }
        }
    }

    public interface OnDeleteProduct {
        void onDelete(String id);
    }
}
