package com.example.zunezxapp.ui.history;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.zunezxapp.base.BaseViewModel;
import com.example.zunezxapp.base.entity.BaseObjectResponse;
import com.example.zunezxapp.base.entity.Result;
import com.example.zunezxapp.entity.Order;
import com.example.zunezxapp.preferences.SharedPref;
import com.example.zunezxapp.repository.Repository;

import javax.inject.Inject;

public class HistoryViewModel extends BaseViewModel {

    private final Repository repository;
    private final SharedPref sharedPref;

    private MutableLiveData<BaseObjectResponse<Result<Order>>> orderLiveData = new MutableLiveData<>();

    public MutableLiveData<BaseObjectResponse<Result<Order>>> getOrderLiveData() {
        return orderLiveData;
    }

    public void setOrderLiveData(MutableLiveData<BaseObjectResponse<Result<Order>>> orderLiveData) {
        this.orderLiveData = orderLiveData;
    }

    @Inject
    public HistoryViewModel(Repository repository, SharedPref sharedPref) {
        this.repository = repository;
        this.sharedPref = sharedPref;
    }

    public void getCustomerOrder() {
        compositeDisposable.add(repository.getCustomerOrder(sharedPref.getToken())
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                })
                .doFinally(() -> {
                    loading.setValue(false);
                })
                .subscribe(resultBaseObjectResponse -> {
                    orderLiveData.setValue(resultBaseObjectResponse);
                }, throwable -> {
                    Log.d("checkorder", "getCustomerOrder: " + throwable.getLocalizedMessage());
                }));
    }
}
