package com.example.zunezxapp.ui.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.zunezxapp.base.BaseViewModel;
import com.example.zunezxapp.entity.HomeCategory;
import com.example.zunezxapp.entity.HomeProduct;
import com.example.zunezxapp.entity.SubCategory;
import com.example.zunezxapp.repository.Repository;

import java.util.List;

import javax.inject.Inject;

public class HomeViewModel extends BaseViewModel {

    private final Repository repository;

    private MutableLiveData<List<HomeCategory>> listHomeCate = new MutableLiveData<>();
    private MutableLiveData<List<HomeProduct>> listHomeProduct = new MutableLiveData<>();
    private MutableLiveData<List<SubCategory>> listSubCate = new MutableLiveData<>();

    public MutableLiveData<List<SubCategory>> getListSubCate() {
        return listSubCate;
    }

    public void setListSubCate(MutableLiveData<List<SubCategory>> listSubCate) {
        this.listSubCate = listSubCate;
    }

    public MutableLiveData<List<HomeProduct>> getListHomeProduct() {
        return listHomeProduct;
    }

    public void setListHomeProduct(MutableLiveData<List<HomeProduct>> listHomeProduct) {
        this.listHomeProduct = listHomeProduct;
    }

    public MutableLiveData<List<HomeCategory>> getListHomeCate() {
        return listHomeCate;
    }

    public void setListHomeCate(MutableLiveData<List<HomeCategory>> listHomeCate) {
        this.listHomeCate = listHomeCate;
    }

    @Inject
    public HomeViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getCategory() {
        compositeDisposable.add(repository.getHomeCategory()
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                })
                .doFinally(() -> {
                    loading.setValue(false);
                })
                .subscribe(
                        resultBaseObjectResponse -> {
                            listHomeCate.setValue(resultBaseObjectResponse.getData().getResult());
                        }, throwable -> {
                            Log.d("checkcheck", "getCategory: " + throwable.getLocalizedMessage());
                        }
                ));
    }

    public void getHomeProduct() {
        compositeDisposable.add(repository.getHomeProduct()
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                })
                .doFinally(() -> {
                    loading.setValue(false);
                }).subscribe(
                        resultBaseObjectResponse -> {
                            listHomeProduct.setValue(resultBaseObjectResponse.getData().getResult());
                        }, throwable -> {
                            Log.d("checkcheck", "getHomeProduct: " + throwable.getLocalizedMessage());
                        }
                ));
    }

}
