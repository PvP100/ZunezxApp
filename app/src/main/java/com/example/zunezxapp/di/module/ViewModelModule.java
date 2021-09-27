package com.example.zunezxapp.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.example.zunezxapp.di.ViewModelFactory;
import com.example.zunezxapp.di.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    //bind ViewModel
//    @Binds
//    @IntoMap
//    @ViewModelKey(MainViewModel.class)
//    abstract ViewModel bindMainViewModel(MainViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
