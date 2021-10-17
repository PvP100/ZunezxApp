package com.example.zunezxapp.ui.categorydetail;

import androidx.lifecycle.MutableLiveData;

import com.example.zunezxapp.base.BaseViewModel;
import com.example.zunezxapp.entity.HomeProduct;
import com.example.zunezxapp.repository.Repository;

import java.util.List;

import javax.inject.Inject;

public class SearchViewModel extends BaseViewModel {

    private final Repository repository;

    private MutableLiveData<List<HomeProduct>> listHomeProduct = new MutableLiveData<>();

    public MutableLiveData<List<HomeProduct>> getListHomeProduct() {
        return listHomeProduct;
    }

    public void setListHomeProduct(MutableLiveData<List<HomeProduct>> listHomeProduct) {
        this.listHomeProduct = listHomeProduct;
    }

    @Inject
    public SearchViewModel(Repository repository) {
        this.repository = repository;
    }

    public void search(String productName) {
        compositeDisposable.add(repository.searchByProductName(productName)
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                })
                .doFinally(() -> {
                    loading.setValue(false);
                })
                .subscribe(
                        resultBaseObjectResponse -> {
                            listHomeProduct.setValue(resultBaseObjectResponse.getData().getResult());
                        },
                        throwable -> {

                        }
                ));
    }

}
