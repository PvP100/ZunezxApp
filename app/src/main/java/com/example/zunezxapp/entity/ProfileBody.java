package com.example.zunezxapp.entity;

public class ProfileBody {

    private String address;
    private String avatarUrl;
    private String birthday;
    private String email;
    private String fullName;
    private int gender;
    private String phone;

    public ProfileBody(String address, String avatarUrl, String birthday, String email, String fullName, int gender, String phone) {
        this.address = address;
        this.avatarUrl = avatarUrl;
        this.birthday = birthday;
        this.email = email;
        this.fullName = fullName;
        this.gender = gender;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
