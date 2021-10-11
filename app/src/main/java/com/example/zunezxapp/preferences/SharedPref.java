package com.example.zunezxapp.preferences;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedPref {

    private SharedPreferences preferences;

    private static final String PREF_KEY_USER_ID = "PREF_KEY_USER_ID";
    private static final String PREF_KEY_STATUS = "PREF_KEY_STATUS";

    @Inject
    public SharedPref(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void setToken(String token) {
        preferences.edit().putString(PREF_KEY_USER_ID, token).apply();
    }

    public String getToken() {
        return preferences.getString(PREF_KEY_USER_ID, null);
    }

    public void setStatus(boolean status) {
        preferences.edit().putBoolean(PREF_KEY_STATUS, status).apply();
    }

    public boolean getStatus() {
        return preferences.getBoolean(PREF_KEY_STATUS, false);
    }

}
