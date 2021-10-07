package com.example.zunezxapp.ui.login;

import android.util.Base64;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.zunezxapp.base.BaseViewModel;
import com.example.zunezxapp.repository.Repository;

import javax.inject.Inject;

public class LoginViewModel extends BaseViewModel {
    private final Repository repository;

    private MutableLiveData<Boolean> status = new MutableLiveData<>();

    public MutableLiveData<Boolean> getMessage() {
        return status;
    }

    public void setMessage(MutableLiveData<Boolean> status) {
        this.status = status;
    }

    @Inject
    public LoginViewModel(Repository repository) {
        this.repository = repository;
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
                    Log.d("hellooo", "userLogin: " + respone.getData().getCustomerId());
                }, throwable -> {
                    status.setValue(false);
                    Log.d("hellooo", "userFail: " + throwable.getLocalizedMessage());
                }));
    }
}
