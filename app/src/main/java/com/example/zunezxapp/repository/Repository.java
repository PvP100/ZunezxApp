package com.example.zunezxapp.repository;


import com.example.zunezxapp.api.ApiService;
import com.example.zunezxapp.base.entity.BaseObjectResponse;
import com.example.zunezxapp.entity.HomeCategory;
import com.example.zunezxapp.entity.HomeProduct;
import com.example.zunezxapp.entity.LoginRespone;
import com.example.zunezxapp.entity.ProductDetail;
import com.example.zunezxapp.entity.Profile;
import com.example.zunezxapp.base.entity.Result;
import com.example.zunezxapp.entity.SubCategory;
import com.example.zunezxapp.entity.User;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

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

    public Single<BaseObjectResponse<Profile>> getProfile(String userId) {
        return apiService.getProfile(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<BaseObjectResponse<Result<HomeCategory>>> getHomeCategory() {
        return apiService.getHomeCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<BaseObjectResponse<Result<HomeProduct>>> getHomeProduct() {
        return apiService.getHomeProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<BaseObjectResponse<ProductDetail>> getProductDetail(String productId) {
        return apiService.getProductDetail(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
