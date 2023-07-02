package com.example.freechat.services

import android.util.Log
import com.example.freechat.util.AppPref
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebasePushService: FirebaseMessagingService() {


    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.e(TAG, "onNewToken: $token")
        AppPref.getInstance(this).setFirebaseToken(token)
    }

    companion object {
        final const val TAG: String = "FirebasePushService"
    }
}