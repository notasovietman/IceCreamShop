package com.example.data.model

import com.google.gson.annotations.SerializedName

data class OrderPositionsModel(
    @SerializedName("icecream_id")
    val icecream_id: Int,
    @SerializedName("quantity")
    val quantity:Int
)