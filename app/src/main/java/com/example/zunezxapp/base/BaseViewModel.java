package com.example.zunezxapp.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;


/**
 *  Created by PvP100 on 27/09/2021.
 */
public abstract class BaseViewModel extends ViewModel {
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
