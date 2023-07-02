package com.example.freechat.util

import android.util.Log

class ErrorLogger {
    fun logError(screenName: String, error: String) {
        Log.e(TAG, "logging error: on $screenName, error : $error")
    }

    companion object {
        const val TAG = "FreeChatLogger"
    }
}