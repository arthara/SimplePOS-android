package com.simple.pos.shared.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.simple.pos.base.util.UtilInterface;
import com.simple.pos.shared.model.Store;
import com.simple.pos.shared.model.Token;

public class StoreUtil extends SharedPreferencesUtil<Store> {
    private final static String SHARED_PREFS_NAME = "StoreSharedPrefs";
    private final static String SESSION_TOKEN = "SessionToken";

    public StoreUtil() {
    }

    public StoreUtil(Context context, String SharedPrefsName) {
        super(context, SharedPrefsName);
    }

    @Override
    public Store getSessionData() {
        String sessionDataJson = sharedPrefs.getString(SESSION_TOKEN, null);
        if (sessionDataJson != null) {
            return new Gson().fromJson(sessionDataJson, Store.class);
        }
        return null;
    }

    @Override
    void setSessionData(Store sessionData) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(SESSION_TOKEN, new Gson().toJson(sessionData));
        editor.apply();
    }

    @Override
    public Store initialize(Store sessionData) {
        return super.initialize(sessionData);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void update(Store sessionData) {
        super.update(sessionData);
    }

    @Override
    public UtilInterface create(Context context) {
        return new StoreUtil(context, SHARED_PREFS_NAME);
    }
}
