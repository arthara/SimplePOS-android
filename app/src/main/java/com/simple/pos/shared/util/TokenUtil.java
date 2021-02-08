package com.simple.pos.shared.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.simple.pos.base.util.UtilInterface;
import com.simple.pos.shared.model.Token;

public class TokenUtil extends SharedPreferencesUtil<Token> {
    private final static String SHARED_PREFS_NAME = "TokenSharedPrefs";
    private final static String SESSION_TOKEN = "SessionToken";

    public TokenUtil() {
    }

    public TokenUtil(Context context, String SharedPrefsName) {
        super(context, SharedPrefsName);
    }

    @Override
    public Token getSessionData() {
        String sessionDataJson = sharedPrefs.getString(SESSION_TOKEN, null);
        if (sessionDataJson != null) {
            return new Gson().fromJson(sessionDataJson, Token.class);
        }
        return null;
    }

    @Override
    void setSessionData(Token sessionData) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(SESSION_TOKEN, new Gson().toJson(sessionData));
        editor.apply();
    }

    @Override
    public Token initialize(Token sessionData) {
        return super.initialize(sessionData);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void update(Token sessionData) {
        super.update(sessionData);
    }

    @Override
    public UtilInterface create(Context context) {
        return new TokenUtil(context, SHARED_PREFS_NAME);
    }
}
