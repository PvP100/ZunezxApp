package com.example.zunezxapp.ui.productdetail;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.zunezxapp.base.BaseViewModel;
import com.example.zunezxapp.entity.ProductDetail;
import com.example.zunezxapp.preferences.SharedPref;
import com.example.zunezxapp.repository.Repository;


import javax.inject.Inject;

public class ProductDetailViewModel extends BaseViewModel {
    private final Repository repository;

    private MutableLiveData<ProductDetail> productDetail = new MutableLiveData<>();

    public MutableLiveData<ProductDetail> getProductDetailLive() {
        return productDetail;
    }

    public void setProductDetailLive(MutableLiveData<ProductDetail> productDetail) {
        this.productDetail = productDetail;
    }

    @Inject
    public ProductDetailViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getProductDetail(String productId) {
        compositeDisposable.add(repository.getProductDetail(productId)
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                })
                .doFinally(() -> {
                    loading.setValue(false);
                }).subscribe(productDetailBaseObjectResponse -> {
                    productDetail.setValue(productDetailBaseObjectResponse.getData());
                }, throwable -> {
                    Log.d("checkcheck", "getProductDetail: ");
                }));
    }
}
