package com.example.data.api

import com.example.data.model.OrderModel
import com.example.data.model.OrderPositionsModel
import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface OrderApi {

    @POST("api/order/new")
    fun createOrder(@Header("Authorization") authorization: String, @Body order: List<OrderPositionsModel>): Completable
}