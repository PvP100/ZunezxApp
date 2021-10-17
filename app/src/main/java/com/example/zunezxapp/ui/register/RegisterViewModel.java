package com.example.zunezxapp.ui.register;

import android.util.Base64;

import com.example.zunezxapp.base.BaseViewModel;
import com.example.zunezxapp.entity.ProfileBody;
import com.example.zunezxapp.repository.Repository;

import javax.inject.Inject;

public class RegisterViewModel extends BaseViewModel {

    private final Repository repository;

    @Inject
    public RegisterViewModel(Repository repository) {
        this.repository = repository;
    }

    public void register(
            String password,
            String address,
            String birthday,
            String email,
            String fullName,
            int gender,
            String phone
    ) {
        ProfileBody registerBody = new ProfileBody(
                address,
                "",
                birthday,
                email,
                fullName,
                gender,
                phone
        );
        String base64 = email + ":" + password;
        String header = "Basic " + Base64.encodeToString(base64.getBytes(), Base64.NO_WRAP);
        compositeDisposable.add(repository.regis(header, registerBody)
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                })
                .doFinally(() -> {
                    loading.setValue(false);
                })
                .subscribe(
                        baseObjectResponse -> {
                            status.setValue(true);
                        },
                        throwable -> {
                            status.setValue(false);
                        }
                ));
    }
}
