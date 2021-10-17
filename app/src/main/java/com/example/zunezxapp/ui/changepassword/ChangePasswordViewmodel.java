package com.example.zunezxapp.ui.changepassword;

import android.util.Log;

import com.example.zunezxapp.base.BaseViewModel;
import com.example.zunezxapp.base.entity.BaseObjectResponse;
import com.example.zunezxapp.entity.NewPassword;
import com.example.zunezxapp.preferences.SharedPref;
import com.example.zunezxapp.repository.Repository;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class ChangePasswordViewmodel extends BaseViewModel {

    private final Repository repository;
    private final SharedPref sharedPref;

    @Inject
    public ChangePasswordViewmodel(Repository repository, SharedPref sharedPref) {
        this.repository = repository;
        this.sharedPref = sharedPref;
    }

    public void changePassword(String oldPassword, String newPassword) {
        compositeDisposable.add(repository.changePassword(sharedPref.getToken(), new NewPassword(oldPassword, newPassword))
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
