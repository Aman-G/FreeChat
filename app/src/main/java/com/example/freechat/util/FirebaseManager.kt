package com.example.freechat.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging

object FirebaseManager {
    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val fireStore: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }

    fun getFirebaseAuthentication(): FirebaseAuth = firebaseAuth

    fun getFirebaseDatastore(): FirebaseFirestore = fireStore

//    fun getFirebaseMessaging(): FirebaseMessaging = FirebaseMessaging.getInstance()
}