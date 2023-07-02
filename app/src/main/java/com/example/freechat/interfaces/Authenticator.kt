package com.example.freechat.interfaces

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.freechat.util.SignInResult

interface Authenticator {
    suspend fun signInWithEmailAndPassword(email: String, password: String)

    suspend fun createAccount(email: String, password: String)

    suspend fun resetPassword(email: String)

    var result: MutableLiveData<SignInResult>
}