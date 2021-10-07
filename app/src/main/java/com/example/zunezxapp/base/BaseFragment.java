package com.example.zunezxapp.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.zunezxapp.custom.LoadingDialog;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment<VM extends BaseViewModel, T extends ViewDataBinding> extends Fragment {

    private ViewController viewController = null;

    protected VM viewModel;

    protected abstract VM creatViewModel();

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    protected T binding;

    protected LoadingDialog loadingDialog;

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, layoutId(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingDialog = new LoadingDialog(requireContext());
        viewModel = creatViewModel();
        initView();
        initData();
        initListener();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        loadingDialog.dismiss();
    }

    protected abstract void backFromAddFragment();

    protected abstract int layoutId();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void initListener();
    protected abstract boolean backPressed();

    public void setVC(ViewController viewController) {
        this.viewController = viewController;
    }

    protected ViewController getVC() {
        if (viewController == null) {
            viewController = ((BaseActivity) getActivity()).getViewController();
        }
        return viewController;
    }

}
