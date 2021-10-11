package com.example.zunezxapp.base.entity;

import java.util.List;

public class Result<T> {
    private List<T> results;

    public List<T> getResult() {
        return results;
    }

    public void setResult(List<T> results) {
        this.results = results;
    }
}
