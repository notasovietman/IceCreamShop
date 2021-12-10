package com.example.data.api

import com.example.data.model.IceCreamModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface IceCreamApi {

    @GET("/api/icecream")
    fun getIceCreamList() : Single<List<IceCreamModel>>

    @GET("api/icecream/{item_id}")
    fun getIceCreamById(@Path("item_id") id: Int) : Single<IceCreamModel>
}