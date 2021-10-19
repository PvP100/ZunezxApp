package com.example.zunezxapp.ui.confirm;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.zunezxapp.base.BaseViewModel;
import com.example.zunezxapp.entity.Cart;
import com.example.zunezxapp.entity.OrderBody;
import com.example.zunezxapp.entity.Profile;
import com.example.zunezxapp.preferences.SharedPref;
import com.example.zunezxapp.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

public class ConfirmViewModel extends BaseViewModel {

    private Realm realm;

    private final Repository repository;

    private final SharedPref sharedPref;

    private RealmResults<Cart> listCart;

    private MutableLiveData<List<Cart>> listCartLiveDataConfirm = new MutableLiveData<>();

    private MutableLiveData<Profile> profileMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<Profile> getProfileMutableLiveData() {
        return profileMutableLiveData;
    }

    public void setProfileMutableLiveData(MutableLiveData<Profile> profileMutableLiveData) {
        this.profileMutableLiveData = profileMutableLiveData;
    }

    public MutableLiveData<List<Cart>> getListCartLiveDataConfirm() {
        return listCartLiveDataConfirm;
    }

    public void setListCartLiveDataConfirm(MutableLiveData<List<Cart>> listCartLiveDataConfirm) {
        this.listCartLiveDataConfirm = listCartLiveDataConfirm;
    }

    @Inject
    public ConfirmViewModel(Realm realm, Repository repository, SharedPref sharedPref) {
        this.realm = realm;
        this.sharedPref = sharedPref;
        this.repository = repository;
    }

    public void getCart() {
        listCart = realm.where(Cart.class).findAll();
        listCartLiveDataConfirm.setValue(listCart);
    }

    public void getInfo() {
        compositeDisposable.add(repository.getProfile(sharedPref.getToken())
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                })
                .doFinally(() -> {
                    loading.setValue(false);
                }).subscribe(profileBaseObjectResponse -> {
                    profileMutableLiveData.setValue(profileBaseObjectResponse.getData());
                }, throwable -> {

                }));
    }

    public void createOrder() {
        List<Cart> check = realm.copyFromRealm(listCart);
        compositeDisposable.add(repository.createOrder(new OrderBody(
                sharedPref.getToken(), check
        ))
                .doOnSubscribe(disposable -> {
                    loading.setValue(true);
                })
                .doFinally(() -> {
                    loading.setValue(false);
                })
                .subscribe(
                        baseObjectResponse -> {
                            status.setValue(true);
                        },
                        throwable -> {
                            status.setValue(false);
                        }
                ));
    }

    public void deleteCart() {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Log.d("huhu", "onError: " + error.getLocalizedMessage());
            }
        });
    }

}
