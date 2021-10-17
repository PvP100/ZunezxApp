package com.example.zunezxapp.ui.register;

import android.util.Base64;

import com.example.zunezxapp.base.BaseViewModel;
import com.example.zunezxapp.base.entity.BaseObjectResponse;
import com.example.zunezxapp.entity.ProfileBody;
import com.example.zunezxapp.repository.Repository;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

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
}
