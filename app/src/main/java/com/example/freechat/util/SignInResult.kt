package com.example.freechat.util

sealed class SignInResult {
    data class Success(val message: String) : SignInResult()
    data class Error(val message: String) : SignInResult() {
        fun getErrorMessage(): String {
            return message
        }
    }
}
