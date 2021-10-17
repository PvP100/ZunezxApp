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

    protected MutableLiveData<Boolean> status = new MutableLiveData<>();

    protected MutableLiveData<String> messageError = new MutableLiveData<>();

    public MutableLiveData<String> getMessageError() {
        return messageError;
    }

    public void setMessageError(MutableLiveData<String> messageError) {
        this.messageError = messageError;
    }

    public MutableLiveData<Boolean> getStatus() {
        return status;
    }

    public void setStatus(MutableLiveData<Boolean> status) {
        this.status = status;
    }

    public void setLoading(MutableLiveData<Boolean> loading) {
        this.loading = loading;
    }

    public MutableLiveData<Boolean> getLoading() {
        return loading;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
