package com.example.data.model

import com.google.gson.annotations.SerializedName

data class IceCreamModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("img_url")
    val imgURL: String
)