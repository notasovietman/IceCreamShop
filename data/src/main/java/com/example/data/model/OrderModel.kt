package com.example.data.model

import com.google.gson.annotations.SerializedName

data class OrderModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("user_login")
    val user_login: String,
    @SerializedName("created_at")
    val created_at: String,
    @SerializedName("positions")
    val positions: OrderPositionsModel
)