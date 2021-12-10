package com.example.data.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("login")
    var login: String,
    @SerializedName("password")
    var password: String
)