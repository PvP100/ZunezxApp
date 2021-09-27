package com.example.zunezxapp.repository;


import com.example.zunezxapp.api.ApiService;

import javax.inject.Inject;

import okhttp3.RequestBody;

public class Repository {
    private ApiService apiService;

    @Inject
    public Repository(ApiService apiService) {
        this.apiService = apiService;
    }

}
