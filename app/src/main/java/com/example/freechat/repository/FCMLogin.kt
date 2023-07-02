package com.example.freechat.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.freechat.data.UserProfile
import com.example.freechat.interfaces.Authenticator
import com.example.freechat.util.AppPref
import com.example.freechat.util.ErrorLogger
import com.example.freechat.util.SignInResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FCMLogin(
    private val logger: ErrorLogger,
    private val context: Context,
    override var result: MutableLiveData<SignInResult>
) : Authenticator {
    private val auth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore

    override suspend fun signInWithEmailAndPassword(email: String, password: String) {
        try {
            auth.signInWithEmailAndPassword(email, password).await()
            result.value = SignInResult.Success("Logged In Successfully.")
        } catch (e: Exception) {
            logger.logError("signIn", e.message.toString())
            result.value = SignInResult.Error(
                if (e is FirebaseAuthInvalidUserException) {
                    "Either this email and/or password is incorrect, or this user doesn't exist."
                } else {
                    "Something went wrong, please try again."
                }
            )
        }
    }

    override suspend fun createAccount(email: String, password: String) {
        try {
            auth.createUserWithEmailAndPassword(email, password).await()
            logger.logError("LOGIN", auth.currentUser?.displayName.toString())
            sendVerification()
            insertUser()
            result.value =
                SignInResult.Success("Account Created Successfully. A verification email link has been sent to you email, please click on it to verify your email.")
        } catch (e: Exception) {
            logger.logError("signIn", e.message.toString())
            result.value = SignInResult.Error(e.message.toString())
        }
    }

    override suspend fun resetPassword(email: String) {
        try {
            auth.sendPasswordResetEmail(email)
            result.value = SignInResult.Success("Password Reset mail sent to your email id.")
        } catch (e: Exception) {
            result.value = SignInResult.Error(e.message.toString())
        }
    }

    private suspend fun sendVerification() {
        try {
            auth.currentUser?.let {
                it.sendEmailVerification().await()
            }
        } catch (e: Exception) {
            logger.logError("signIn", e.message.toString())
            result.value = SignInResult.Error(e.message.toString())
        }
    }

    private suspend fun insertUser() {
        val profile = UserProfile(
            uId = auth.currentUser?.uid.toString(),
            userName = auth.currentUser?.email?.split("@")?.get(1) ?: "",
            token = AppPref.getInstance(context).getFirebaseToken().toString(),
            image = ""
        )

        try {
            db.collection("Users").add(profile).await()
        } catch (e: Exception) {

        }
    }
}