package com.example.zunezxapp.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.zunezxapp.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPrefModule {

    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences(App application) {
        return application.getSharedPreferences("zunezx", Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    public App provideApp(Application application) {
        return (App) application;
    }
}
