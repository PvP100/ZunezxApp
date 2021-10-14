package com.example.zunezxapp.adapter;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

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

    Realm realm;

    private List<Cart> list = new ArrayList<>();

    CartViewModel cartViewModel;

    DecimalFormat format = new DecimalFormat("###,###,###");

    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public void setListCart(List<Cart> list) {
        this.list = list;
    }

    public void setTotalCart(Realm realm, CartViewModel viewModel) {
        this.realm = realm;
        cartViewModel = viewModel;
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
        viewBinderHelper.bind(orderViewHolder.binding.swipeToDelete, list.get(position).getId());
        ((OrderViewHolder) holder).bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OrderViewHolder extends BaseViewHolder<Cart> implements View.OnClickListener {

        CartItemBinding binding;
        int count = 1;

        public OrderViewHolder(CartItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        protected void bind(Cart data) {
            count = data.getQuantity();
            Glide.with(binding.getRoot()).load(data.getAvatarUrl()).into(binding.imgProductCart);
            binding.tvProductNameCartItem.setText(data.getName());
            binding.tvPriceCart.setText(format.format(((int) data.getPrice())) + "đ");
            binding.tvPriceCart.setText(format.format(((int) data.getPrice()) * count) + "đ");
            binding.tvCountProductCartItem.setText(String.valueOf(data.getQuantity()));
            binding.icMinusCartItem.setOnClickListener(this);
            binding.icPlusCartItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view == binding.icPlusCartItem) {
                count++;
                binding.tvCountProductCartItem.setText(String.valueOf(count));
                double total = list.get(getAdapterPosition()).getPrice() * count;
//                totalCart += (int) list.get(getAdapterPosition()).getPrice();
                binding.tvPriceCart.setText(format.format(((int) total)) + "đ");
//                totalPrice.setValue(totalCart);
                realm.beginTransaction();
                list.get(getAdapterPosition()).setQuantity(count);
                realm.commitTransaction();
                cartViewModel.onChangeCart();
            } else if (view == binding.icMinusCartItem) {
                if (count >= 2) {
                    count--;
                    binding.tvCountProductCartItem.setText(String.valueOf(count));
                    double total = list.get(getAdapterPosition()).getPrice() * count;
//                    totalCart -= (int) list.get(getAdapterPosition()).getPrice();
                    binding.tvPriceCart.setText(format.format(((int) total)) + "đ");
//                    totalPrice.setValue(totalCart);
                    realm.beginTransaction();
                    list.get(getAdapterPosition()).setQuantity(count);
                    realm.commitTransaction();
                    cartViewModel.onChangeCart();
                }
            }
        }
    }
}
