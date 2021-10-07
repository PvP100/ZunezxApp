package com.example.zunezxapp.entity;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("customerID")
    private String customerId;
    private String fullName;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
