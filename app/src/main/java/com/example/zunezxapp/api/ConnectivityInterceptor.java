package com.example.zunezxapp.api;

import android.content.Context;


import com.example.zunezxapp.exception.NoInternetConnectionException;
import com.example.zunezxapp.ultis.Ultis;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ConnectivityInterceptor implements Interceptor {
    private Context context;

    public ConnectivityInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        if(!Ultis.checkNetwork(context)) {
            throw new NoInternetConnectionException();
        }
        Request.Builder builder = chain.request().newBuilder();
        return chain.proceed(builder.build());
    }
}
