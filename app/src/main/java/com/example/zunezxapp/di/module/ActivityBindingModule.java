package com.example.zunezxapp.di.module;

import com.example.zunezxapp.MainActivity;
import com.example.zunezxapp.ui.LoginFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector
    abstract MainActivity bindMainActivity();
}
