package com.example.zunezxapp.ui.cart;

import androidx.lifecycle.MutableLiveData;

import com.example.zunezxapp.base.BaseViewModel;
import com.example.zunezxapp.entity.Cart;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

public class CartViewModel extends BaseViewModel {

    RealmResults<Cart> listCart;

    private MutableLiveData<List<Cart>> listCartLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Cart>> getListCartLiveData() {
        return listCartLiveData;
    }

    public void setListCartLiveData(MutableLiveData<List<Cart>> listCartLiveData) {
        this.listCartLiveData = listCartLiveData;
    }

    private Realm realm;

    @Inject
    public CartViewModel(Realm realm) {
        this.realm = realm;
    }

    public void getCart() {
        listCart = realm.where(Cart.class).findAll();
        listCartLiveData.setValue(listCart);
    }

}
