package com.example.zunezxapp.adapter;

import android.view.View;

import com.example.zunezxapp.R;
import com.example.zunezxapp.base.BaseAdapter;
import com.example.zunezxapp.databinding.HomeFragmentItemBinding;
import com.example.zunezxapp.databinding.ProductHomeItemBinding;
import com.example.zunezxapp.entity.Home;
import com.example.zunezxapp.entity.HomeCategory;

public class HomeCategoryAdapter extends BaseAdapter<ProductHomeItemBinding> {

    private SetOnCategoryOnClick setOnCategoryOnClick;

    public void setCateListener(SetOnCategoryOnClick setOnCategoryOnClick) {
        this.setOnCategoryOnClick = setOnCategoryOnClick;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.product_home_item;
    }

    @Override
    protected BaseViewHolder solvedOnCreateViewHolder(ProductHomeItemBinding binding) {
        return new HomeCategoryViewHolder(binding, setOnCategoryOnClick);
    }

    @Override
    protected void solvedOnBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    private class HomeCategoryViewHolder extends BaseViewHolder<HomeCategory> implements View.OnClickListener {
        ProductHomeItemBinding binding;
        SetOnCategoryOnClick setOnCategoryOnClick;

        public HomeCategoryViewHolder(ProductHomeItemBinding binding, SetOnCategoryOnClick setOnCategoryOnClick) {
            super(binding.getRoot());
            this.binding = binding;
            this.setOnCategoryOnClick = setOnCategoryOnClick;

            binding.getRoot().setOnClickListener(this);
        }



        @Override
        protected void bind(HomeCategory data) {
//            binding.tvCategoryTitle.setText("dsfsdfsfds");
        }

        @Override
        public void onClick(View view) {
            setOnCategoryOnClick.onCateOnClick();
        }
    }

    public interface SetOnCategoryOnClick {
        void onCateOnClick();
    }
}
