package com.example.zunezxapp.repository;


import com.example.zunezxapp.api.ApiService;
import com.example.zunezxapp.base.entity.BaseObjectResponse;
import com.example.zunezxapp.entity.HomeCategory;
import com.example.zunezxapp.entity.HomeProduct;
import com.example.zunezxapp.entity.LoginRespone;
import com.example.zunezxapp.entity.NewPassword;
import com.example.zunezxapp.entity.Order;
import com.example.zunezxapp.entity.OrderBody;
import com.example.zunezxapp.entity.OrderDetail;
import com.example.zunezxapp.entity.ProductDetail;
import com.example.zunezxapp.entity.Profile;
import com.example.zunezxapp.base.entity.Result;
import com.example.zunezxapp.entity.ProfileBody;
import com.example.zunezxapp.entity.User;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MultipartBody;

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

    public Single<BaseObjectResponse<Result<HomeProduct>>> getHomeProduct(String cateId) {
        return apiService.getHomeProduct(cateId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<BaseObjectResponse<ProductDetail>> getProductDetail(String productId) {
        return apiService.getProductDetail(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<BaseObjectResponse<Result<Order>>> getCustomerOrder(String customerId) {
        return apiService.getCustomerOrder(customerId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<BaseObjectResponse> changePassword(String customerId, NewPassword newPasswordBody) {
        return apiService.changePassword(customerId, newPasswordBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<BaseObjectResponse<Result<HomeProduct>>> searchByProductName(String productName) {
        return apiService.searchProduct(productName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<BaseObjectResponse<Profile>> updateProfile(String id, ProfileBody profileBody) {
        return apiService.updateProfile(id, profileBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<BaseObjectResponse> regis(String basicAuth, ProfileBody profileBody) {
        return apiService.register(basicAuth, profileBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<BaseObjectResponse<Result<OrderDetail>>> getOrderDetail(String id) {
        return apiService.getOrderDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<BaseObjectResponse> createOrder(OrderBody body) {
        return apiService.createOrder(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<BaseObjectResponse> uploadAvatar(String customerId, MultipartBody.Part avatar) {
        return apiService.uploadAvatarCustomer(customerId, avatar)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
