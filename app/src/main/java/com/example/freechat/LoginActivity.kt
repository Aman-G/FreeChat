package com.example.freechat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import com.example.freechat.viewmodels.LoginViewModel
import com.example.freechat.databinding.ActivityLoginBinding
import com.example.freechat.interfaces.Authenticator
import com.example.freechat.repository.FCMLogin
import com.example.freechat.util.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.async

class LoginActivity : AppCompatActivity() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var fcmLogin: Authenticator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        fcmLogin = FCMLogin(ErrorLogger(), this, MutableLiveData())
        viewModel = LoginViewModel(fcmLogin)
        binding.lifecycleOwner = this
        binding.loginViewModel = viewModel

        fcmLogin.result.observe(this) { result ->
            when (result) {
                is SignInResult.Success -> {
                    showAlertDialog("", result.message) {}
                    binding.txtInput.error = null
                    binding.textInputEmail.error = null
                }
                is SignInResult.Error -> {
                    if (result.message.contains("Password")) {
                        binding.txtInput.error = result.message
                        binding.textInputEmail.error = null
                    } else {
                        binding.textInputEmail.error = result.message
                        binding.txtInput.error = null
                    }
                }
            }
        }

        viewModel.showLoader.observe(this) {
            if (it) showProgressDialog() else hideProgressDialog()
        }
    }

    override fun onStart() {
        super.onStart()
        // check if user is already signed in
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun updateUI(user: FirebaseUser?) {

    }

    private fun reload() {

    }

    companion object {
        private const val TAG = "LoginActivity"
    }
}