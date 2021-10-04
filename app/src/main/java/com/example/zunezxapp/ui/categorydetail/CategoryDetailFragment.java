package com.example.zunezxapp.ui.categorydetail;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.zunezxapp.R;
import com.example.zunezxapp.adapter.CategoryDetailAdapter;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.custom.GridSpacingItemDecoration;
import com.example.zunezxapp.databinding.FragmentCategoryBinding;

public class CategoryDetailFragment extends BaseFragment<CategoryViewModel, FragmentCategoryBinding> implements View.OnClickListener {
    @Override
    protected CategoryViewModel creatViewModel() {
        return null;
    }

    @Override
    protected void backFromAddFragment() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_category;
    }

    @Override
    protected void initView() {
        CategoryDetailAdapter categoryDetailAdapter = new CategoryDetailAdapter();
        binding.rcvCategory.setAdapter(categoryDetailAdapter);
        binding.rcvCategory.addItemDecoration(new GridSpacingItemDecoration(2, 50, true));
        binding.rcvCategory.setLayoutManager(new GridLayoutManager(requireContext(), 2));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        binding.toolbarCategory.setNavigationOnClickListener(this);
    }

    @Override
    protected boolean backPressed() {
        getVC().backFromAddFragment(null);
        return false;
    }

    @Override
    public void onClick(View view) {
        getVC().backFromAddFragment(null);
    }
}
