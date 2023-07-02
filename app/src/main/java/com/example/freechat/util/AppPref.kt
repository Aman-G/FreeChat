package com.example.freechat.util

import android.content.Context
import android.content.SharedPreferences

class AppPref private constructor(context: Context) {
    private val preferences: SharedPreferences

    init {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setFirebaseToken(token: String) {
        setString(FIREBASE_TOKEN, token)
    }

    fun getFirebaseToken(): String? {
        return getString(FIREBASE_TOKEN)
    }

    private fun setString(key: String, value: String) {
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun setInt(key: String, value: Int) {
        val editor = preferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    private fun setBoolean(key: String, value: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    private fun setLong(key: String, value: Long) {
        val editor = preferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    private fun getString(key: String): String? {
        return preferences.getString(key, "")
    }

    private fun getInt(key: String): Int {
        return preferences.getInt(key, 0)
    }

    private fun getBoolean(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }

    private fun getLong(key: String): Long {
        return preferences.getLong(key, 0)
    }

    companion object {
        private const val PREF_NAME = "USER_INFO"
        private const val FIREBASE_TOKEN = "FIREBASE_TOKEN"
        private var INSTANCE: AppPref? = null

        fun getInstance(context: Context): AppPref =
            INSTANCE ?: AppPref(context).also { INSTANCE = it }
    }
}