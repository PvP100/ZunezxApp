package com.example.zunezxapp.ui.profile;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.zunezxapp.base.BaseViewModel;
import com.example.zunezxapp.entity.Profile;
import com.example.zunezxapp.preferences.SharedPref;
import com.example.zunezxapp.repository.Repository;

import javax.inject.Inject;

public class ProfileViewModel extends BaseViewModel {

    private final Repository repository;
    private SharedPref sharedPref;

    private MutableLiveData<Boolean> status = new MutableLiveData<>();
    private MutableLiveData<Profile> profileMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<Profile> getProfileMutableLiveData() {
        return profileMutableLiveData;
    }

    public void setProfileMutableLiveData(MutableLiveData<Profile> profileMutableLiveData) {
        this.profileMutableLiveData = profileMutableLiveData;
    }

    @Inject
    public ProfileViewModel(Repository repository, SharedPref sharedPref) {
        this.repository = repository;
        this.sharedPref = sharedPref;
    }

    public MutableLiveData<Boolean> getMessage() {
        return status;
    }

    public void setMessage(MutableLiveData<Boolean> status) {
        this.status = status;
    }

    public void getProfile() {
        String userId = sharedPref.getToken();
        compositeDisposable.add(repository.getProfile(userId)
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                })
                .doFinally(() -> {
                    loading.setValue(false);
                })
        .subscribe(
                profileBaseObjectResponse -> {
                    profileMutableLiveData.setValue(profileBaseObjectResponse.getData());
                }, throwable -> {
                    Log.d("getProfile", "getProfile: " + throwable.getLocalizedMessage());
                }
        ));
    }

    public void logout() {
        sharedPref.setToken(null);
        sharedPref.setStatus(false);
    }

}
