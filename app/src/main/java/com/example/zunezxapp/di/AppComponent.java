package com.example.zunezxapp.di;

import android.app.Application;

import com.example.zunezxapp.App;
import com.example.zunezxapp.di.module.ActivityBindingModule;
import com.example.zunezxapp.di.module.AppModule;
import com.example.zunezxapp.di.module.FragmentBindingModule;
import com.example.zunezxapp.di.module.NetworkModule;
import com.example.zunezxapp.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        FragmentBindingModule.class,
        AndroidSupportInjectionModule.class,
        ActivityBindingModule.class,
        AppModule.class,
        ViewModelModule.class,
        NetworkModule.class
})
public interface AppComponent {

    void inject(App baseApplication);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}
