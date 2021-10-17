package com.example.zunezxapp.ui.login;

import android.util.Base64;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.zunezxapp.base.BaseViewModel;
import com.example.zunezxapp.base.entity.BaseObjectResponse;
import com.example.zunezxapp.preferences.SharedPref;
import com.example.zunezxapp.repository.Repository;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class LoginViewModel extends BaseViewModel {
    private final Repository repository;
    private SharedPref sharedPref;

    public MutableLiveData<Boolean> getMessage() {
        return status;
    }

    @Inject
    public LoginViewModel(Repository repository, SharedPref sharedPref) {
        this.repository = repository;
        this.sharedPref = sharedPref;
    }

    public void userLogin(String email, String password) {
        String base64 = email + ":" + password;
        String header = "Basic " + Base64.encodeToString(base64.getBytes(), Base64.NO_WRAP);

        compositeDisposable.add(repository.userLogin(header)
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                })
                .doFinally(() -> {
                    loading.setValue(false);
                })
                .subscribe(respone -> {
                    status.setValue(true);
                    sharedPref.setStatus(true);
                    sharedPref.setToken(respone.getData().getCustomerId());
                }, throwable -> {
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
                }));
    }
}
