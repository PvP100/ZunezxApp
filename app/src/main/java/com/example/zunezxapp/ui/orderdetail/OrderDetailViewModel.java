package com.example.zunezxapp.ui.orderdetail;

import androidx.lifecycle.MutableLiveData;

import com.example.zunezxapp.base.BaseViewModel;
import com.example.zunezxapp.entity.Cart;
import com.example.zunezxapp.entity.OrderDetail;
import com.example.zunezxapp.repository.Repository;

import java.util.List;

import javax.inject.Inject;

public class OrderDetailViewModel extends BaseViewModel {

    private final Repository repository;

    private MutableLiveData<List<OrderDetail>> listOrder = new MutableLiveData<>();

    public MutableLiveData<List<OrderDetail>> getListOrder() {
        return listOrder;
    }

    @Inject
    public OrderDetailViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getOrderDetail(String id) {
        compositeDisposable.add(repository.getOrderDetail(id)
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                })
                .doFinally(() -> {
                    loading.setValue(false);
                })
                .subscribe(
                        resultBaseObjectResponse -> {
                            listOrder.setValue(resultBaseObjectResponse.getData().getResult());
                        },
                        throwable -> {

                        }
                ));
    }
}
