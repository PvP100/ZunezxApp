package com.example.zunezxapp.repository;


import com.example.zunezxapp.api.ApiService;
import com.example.zunezxapp.base.entity.BaseObjectResponse;
import com.example.zunezxapp.entity.LoginRespone;
import com.example.zunezxapp.entity.User;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.RequestBody;

public class Repository {
    private ApiService apiService;

    @Inject
    public Repository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<BaseObjectResponse<User>> userLogin(String basicAuth) {
        return apiService.userLogin(basicAuth, new LoginRespone())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
