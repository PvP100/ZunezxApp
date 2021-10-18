package com.example.zunezxapp.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.example.zunezxapp.di.ViewModelFactory;
import com.example.zunezxapp.di.ViewModelKey;
import com.example.zunezxapp.ui.cart.CartViewModel;
import com.example.zunezxapp.ui.categorydetail.SearchViewModel;
import com.example.zunezxapp.ui.changepassword.ChangePasswordViewmodel;
import com.example.zunezxapp.ui.confirm.ConfirmViewModel;
import com.example.zunezxapp.ui.history.HistoryViewModel;
import com.example.zunezxapp.ui.home.HomeViewModel;
import com.example.zunezxapp.ui.login.LoginViewModel;
import com.example.zunezxapp.ui.orderdetail.OrderDetailViewModel;
import com.example.zunezxapp.ui.productdetail.ProductDetailViewModel;
import com.example.zunezxapp.ui.profile.ProfileViewModel;
import com.example.zunezxapp.ui.register.RegisterViewModel;
import com.example.zunezxapp.ui.splash.SplashViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    //bind ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel.class)
    abstract ViewModel bindSplashViewModel(SplashViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProductDetailViewModel.class)
    abstract ViewModel bindProductDetailViewModel(ProductDetailViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel.class)
    abstract ViewModel bindCartViewModel(CartViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel.class)
    abstract ViewModel bindHistoryViewModel(HistoryViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ConfirmViewModel.class)
    abstract ViewModel bindConfirmViewModel(ConfirmViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ChangePasswordViewmodel.class)
    abstract ViewModel bindChangePasswordViewmodel(ChangePasswordViewmodel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel.class)
    abstract ViewModel bindSearchViewModel(SearchViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel.class)
    abstract ViewModel bindRegisterViewModel(RegisterViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(OrderDetailViewModel.class)
    abstract ViewModel bindOrderDetailViewModel(OrderDetailViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
