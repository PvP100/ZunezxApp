package com.example.zunezxapp;


import android.widget.Toast;

import com.example.zunezxapp.base.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected int layoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected int layoutId() {
        return R.id.container;
    }

    @Override
    protected void initView() {
        Toast.makeText(this, "jfhdjsf", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}