package com.example.freechat.util

import java.util.regex.Matcher
import java.util.regex.Pattern

object Validator {
    private const val EMAIL_PATTERN =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

    private val pattern: Pattern = Pattern.compile(EMAIL_PATTERN)
    private var matcher: Matcher? = null

    fun validateEmail(hex: String?): Boolean {
        matcher = pattern.matcher(hex)
        return matcher!!.matches()
    }

    fun validatePassword(password: String): SignInResult {
        return if (password.isBlank()) {
            SignInResult.Error("Password field is blank.")
        } else if (password.length < 4) {
            SignInResult.Error("Password should be at least 4 characters long")
        } else {
            SignInResult.Success("")
        }
    }
}