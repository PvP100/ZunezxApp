package com.example.zunezxapp.base;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.example.zunezxapp.custom.LoadingDialog;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by Kidd on 07/01/2019.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ViewController viewController = null;

    public ViewController getViewController() {
        if (viewController == null) {
            viewController = new ViewController(layoutId(), getSupportFragmentManager());
        }
        return viewController;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(layoutResId());
        viewController = new ViewController(layoutId(), getSupportFragmentManager());
        initView();
        initData();
        initListener();
    }

    @Override
    public void onBackPressed() {
        if (viewController != null && viewController.getCurrentFragment() != null) {
            if (viewController.getCurrentFragment().backPressed() == true) {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    protected abstract int layoutResId();
    protected abstract int layoutId();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void initListener();
}
