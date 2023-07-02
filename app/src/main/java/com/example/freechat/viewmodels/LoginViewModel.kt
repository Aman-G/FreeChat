package com.example.freechat.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freechat.interfaces.Authenticator
import com.example.freechat.repository.FCMLogin
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(private val authenticator: Authenticator) : ViewModel() {
    val showLoader = MutableLiveData(false)
    val emailLiveData = MutableLiveData("")
    val passwordLiveData = MutableLiveData("")

    fun signIn() {
        showLoader.value = true
        viewModelScope.launch {
            val result = viewModelScope.async {
                authenticator.signInWithEmailAndPassword(
                    emailLiveData.value.toString(),
                    passwordLiveData.value.toString()
                )
            }
            result.await()
            showLoader.value = false
        }
    }

    fun createAccount() {
        showLoader.value = true
        viewModelScope.launch {
            val result = viewModelScope.async {
                authenticator.createAccount(
                    emailLiveData.value.toString(),
                    passwordLiveData.value.toString()
                )
            }

            result.await()
            showLoader.value = false
        }
    }

    fun resetPassword() {
        showLoader.value = true
        viewModelScope.launch {
            val result = viewModelScope.async {
                authenticator.resetPassword(emailLiveData.value.toString())
            }

            result.await()
            showLoader.value = false
        }
    }
}