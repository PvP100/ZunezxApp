package com.example.zunezxapp.entity;

import java.util.List;

public class Home {
    private String cateName;
    private List<HomeCategory> homeCategoryList;

    public Home(String cateName, List<HomeCategory> homeCategoryList) {
        this.cateName = cateName;
        this.homeCategoryList = homeCategoryList;
    }

    public List<HomeCategory> getHomeCategoryList() {
        return homeCategoryList;
    }

    public void setHomeCategoryList(List<HomeCategory> homeCategoryList) {
        this.homeCategoryList = homeCategoryList;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
}
