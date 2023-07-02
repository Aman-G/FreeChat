package com.example.freechat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginViewModel : ViewModel() {
    var auth: FirebaseAuth = FirebaseAuth.getInstance()
    var emailLiveData = MutableLiveData("")
    var passwordLiveData = MutableLiveData("")
    var resultLiveData = MutableLiveData<SignInResult>()
    var showLoader = MutableLiveData(false)

    fun createAccount() {
        showLoader.value = true
        auth.createUserWithEmailAndPassword(emailLiveData.value ?: "", passwordLiveData.value ?: "")
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    val result = task.result.user?.let {
                        SignInResult.Success(
                            it,
                            "Account created successfully. A verification link has been sent to your email."
                        )
                    }
                        ?: SignInResult.Error(
                            "Authentication failed: ${task.exception?.message}",
                            "Something went wrong, please try again."
                        )
                    resultLiveData.value = result
                    sendVerificationEmail()
                } else {
                    val result = SignInResult.Error(
                        task.exception?.message ?: "Unknown Error",
                        "Something went wrong, please try again."
                    )
                    resultLiveData.value = result
                }
                showLoader.value = false
            }
    }

    private fun sendVerificationEmail() {
        val user = auth.currentUser
        user?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {

                } else {

                }
            }
        }
    }

    fun signIn() {
        showLoader.value = true
        auth.signInWithEmailAndPassword(emailLiveData.value ?: "", passwordLiveData.value ?: "")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val result =
                        task.result.user?.let { SignInResult.Success(it, "Logged in successfully") }
                            ?: SignInResult.Error(
                                "Authentication failed: ${task.exception?.message}",
                                "Something went wrong, please try again."
                            )
                    resultLiveData.value = result
                } else {
                    val result = SignInResult.Error(
                        task.exception?.message ?: "Unknown Error",
                        "Something went wrong, please try again."
                    )
                    resultLiveData.value = result
                }
                showLoader.value = false
            }
    }

    sealed class SignInResult {
        data class Success(val user: FirebaseUser, val message: String) : SignInResult()
        data class Error(val exception: String, val message: String) : SignInResult()
    }
}
