package com.alexeymorozua.codesample.mvp.data.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by john on 25.11.2016.
 */

public class PreferencesHelper {

  private static final String PREF_FILE_NAME = "code_sample";

  private static final String TOKEN = "token";

  private final SharedPreferences mPreferences;

  public PreferencesHelper(Context context) {
    mPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
  }

  public String getToken() {
    return mPreferences.getString(TOKEN, "");
  }

  public void setToken(String token) {
    mPreferences.edit().putString(TOKEN, token).apply();
  }

  public void clear() {
    mPreferences.edit().clear().apply();
  }
}
