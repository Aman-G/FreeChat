package com.example.freechat.data

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class UserProfile(
    var uId: String,
    @ServerTimestamp
    var createdAt: Date? = null,
    @ServerTimestamp
    var updatedAt: Date? = null,
    var image: String = "",

    @set:PropertyName("user_name")
    var userName: String = "",
    var token: String = "",
    var mobile: MobileNumber? = null
)

data class MobileNumber(
    @set:PropertyName("country_code")
    var countryCode: String = "",
    var number: String = ""
)