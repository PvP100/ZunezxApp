package com.example.zunezxapp.ui.cart;

import android.util.Log;

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

    private MutableLiveData<Integer> totalPrice = new MutableLiveData<>();

    public MutableLiveData<Integer> getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(MutableLiveData<Integer> totalPrice) {
        this.totalPrice = totalPrice;
    }

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

    public void onChangeCart() {
        listCart.addChangeListener(results -> {
            listCartLiveData.setValue(results);
        });
    }

    public void deleteProductInCart(String id) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Cart cart = realm.where(Cart.class).equalTo("id", id).findFirst();
                cart.deleteFromRealm();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d("huhu", "onError: " + error.getLocalizedMessage());
            }
        });
    }

}
