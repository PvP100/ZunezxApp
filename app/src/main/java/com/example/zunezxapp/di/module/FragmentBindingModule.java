package com.example.zunezxapp.di.module;

import com.example.zunezxapp.ui.cart.CartFragment;
import com.example.zunezxapp.ui.categorydetail.SearchFragment;
import com.example.zunezxapp.ui.changepassword.ChangePasswordFragment;
import com.example.zunezxapp.ui.confirm.ConfirmFragment;
import com.example.zunezxapp.ui.history.HistoryFragment;
import com.example.zunezxapp.ui.home.HomeFragment;
import com.example.zunezxapp.ui.login.LoginFragment;
import com.example.zunezxapp.ui.main.MainFragment;
import com.example.zunezxapp.ui.orderdetail.OrderDetailFragment;
import com.example.zunezxapp.ui.productdetail.ProductDetailFragment;
import com.example.zunezxapp.ui.profile.ProfileFragment;
import com.example.zunezxapp.ui.register.RegisterFragment;
import com.example.zunezxapp.ui.splash.SplashFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBindingModule {
    @ContributesAndroidInjector
    abstract SplashFragment bindSplashFragment();

    @ContributesAndroidInjector
    abstract LoginFragment bindLoginFragment();

    @ContributesAndroidInjector
    abstract MainFragment bindMainFragment();

    @ContributesAndroidInjector
    abstract HomeFragment bindHomeFragment();

    @ContributesAndroidInjector
    abstract ProfileFragment bindProfileFragment();

    @ContributesAndroidInjector
    abstract HistoryFragment bindHistoryFragment();

    @ContributesAndroidInjector
    abstract CartFragment bindCartFragment();

    @ContributesAndroidInjector
    abstract SearchFragment bindCategoryDetail();

    @ContributesAndroidInjector
    abstract ChangePasswordFragment bindChangePassword();

    @ContributesAndroidInjector
    abstract ConfirmFragment bindConfirmFragment();

    @ContributesAndroidInjector
    abstract ProductDetailFragment bindProductDetail();

    @ContributesAndroidInjector
    abstract RegisterFragment bindRegister();

    @ContributesAndroidInjector
    abstract OrderDetailFragment bindOrderDetailFragment();
}
