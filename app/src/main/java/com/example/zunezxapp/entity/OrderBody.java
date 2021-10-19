package com.example.zunezxapp.entity;

import java.util.List;

public class OrderBody {
    private String customerID;
    private List<Cart> details;

    public OrderBody(String customerID, List<Cart> details) {
        this.customerID = customerID;
        this.details = details;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public List<Cart> getDetails() {
        return details;
    }

    public void setDetails(List<Cart> details) {
        this.details = details;
    }
}
