package com.lumut.test.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

object PreferencesUtil {
    const val PREF_TOKEN = "pref_is_token";
    const val PREF_REFRESH_TOKEN = "pref_is_refresh_token";

    private fun sharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun setToken(context: Context, token: String) {
        sharedPreferences(context).edit().putString(PREF_TOKEN, token).apply()
    }

    fun getToken(context: Context): String {
        return sharedPreferences(context).getString(PREF_TOKEN, "") ?: ""
    }

    fun setRefreshToken(context: Context, token: String) {
        sharedPreferences(context).edit().putString(PREF_REFRESH_TOKEN, token).apply()
    }

    fun getRefreshToken(context: Context): String {
        return sharedPreferences(context).getString(PREF_REFRESH_TOKEN, "") ?: ""
    }
}