package com.example.zunezxapp.ui.productdetail;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.zunezxapp.base.BaseViewModel;
import com.example.zunezxapp.entity.Cart;
import com.example.zunezxapp.entity.ProductDetail;
import com.example.zunezxapp.repository.Repository;


import javax.inject.Inject;

import io.realm.Realm;

public class ProductDetailViewModel extends BaseViewModel {
    private final Repository repository;

    private Realm realm;

    private MutableLiveData<ProductDetail> productDetail = new MutableLiveData<>();

    public MutableLiveData<ProductDetail> getProductDetailLive() {
        return productDetail;
    }

    public void setProductDetailLive(MutableLiveData<ProductDetail> productDetail) {
        this.productDetail = productDetail;
    }

    @Inject
    public ProductDetailViewModel(Repository repository, Realm realm) {
        this.repository = repository;
        this.realm = realm;
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

    public void addToCart() {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Cart productCart = realm.createObject(Cart.class);
                productCart.setAvatarUrl(productDetail.getValue().getAvatarUrl());
                productCart.setPrice(productDetail.getValue().getPrice());
                productCart.setName(productDetail.getValue().getName());
                productCart.setId(productDetail.getValue().getId());
                productCart.setQuantity(1);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d("checkcheck", "onSuccess: ");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d("dfdf", "onError: " + error.getLocalizedMessage());
            }
        });
    }
}
