package com.example.zunezxapp.ui.splash;

import androidx.lifecycle.MutableLiveData;

import com.example.zunezxapp.base.BaseViewModel;
import com.example.zunezxapp.preferences.SharedPref;

import javax.inject.Inject;

public class SplashViewModel extends BaseViewModel {

    private SharedPref sharedPref;

    private MutableLiveData<Boolean> loginStatus = new MutableLiveData<>();

    public MutableLiveData<Boolean> getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(MutableLiveData<Boolean> loginStatus) {
        this.loginStatus = loginStatus;
    }

    @Inject
    public SplashViewModel(SharedPref sharedPref) {
        this.sharedPref = sharedPref;
    }

    public void checkLoginStatus() {
        if (sharedPref.getStatus()) {
            loginStatus.setValue(true);
        } else {
            loginStatus.setValue(false);
        }
    }

}
