package com.example.zunezxapp.ui.main;

import androidx.fragment.app.Fragment;

import com.example.zunezxapp.R;
import com.example.zunezxapp.adapter.HomeViewPagerAdapter;
import com.example.zunezxapp.base.BaseFragment;
import com.example.zunezxapp.databinding.FragmentMainBinding;
import com.example.zunezxapp.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainFragment extends BaseFragment<MainViewModel, FragmentMainBinding> {
    @Override
    protected MainViewModel creatViewModel() {
        return null;
    }

    @Override
    protected void backFromAddFragment() {

    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(this, listFragment());
        binding.viewpagerMain.setAdapter(homeViewPagerAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean backPressed() {
        return false;
    }

    private List<BaseFragment> listFragment() {
        return new ArrayList<BaseFragment>(
                (Collection<? extends BaseFragment>) new HomeFragment()
        );
    }
}
