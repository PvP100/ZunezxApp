package com.example.zunezxapp.ui.profile;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.zunezxapp.base.BaseViewModel;
import com.example.zunezxapp.base.entity.BaseObjectResponse;
import com.example.zunezxapp.entity.Profile;
import com.example.zunezxapp.entity.ProfileBody;
import com.example.zunezxapp.preferences.SharedPref;
import com.example.zunezxapp.repository.Repository;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

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

    public void updateProfile(
            String address,
            String birthday,
            String email,
            String fullName,
            int gender,
            String phone
    ) {
        compositeDisposable.add(repository.updateProfile(sharedPref.getToken(), new ProfileBody(
                address,
                "",
                birthday,
                email,
                fullName,
                gender,
                phone
        )).doOnSubscribe(disposable -> {
                    loading.setValue(true);
                })
                .doFinally(() -> {
                    loading.setValue(false);
                })
                .subscribe(
                        resultBaseObjectResponse -> {
                            status.setValue(true);
                            profileMutableLiveData.setValue(resultBaseObjectResponse.getData());
                        },
                        throwable -> {
                            status.setValue(false);
                            if(throwable instanceof HttpException) {
                                ResponseBody body = ((HttpException) throwable).response().errorBody();
                                Gson gson = new Gson();
                                TypeAdapter<BaseObjectResponse> adapter = gson.getAdapter(BaseObjectResponse.class);
                                try {
                                    BaseObjectResponse baseObjectResponse = adapter.fromJson(body.string());
                                    messageError.setValue(baseObjectResponse.getMsg());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                ));
    }

    public void logout() {
        sharedPref.setToken(null);
        sharedPref.setStatus(false);
    }

}
