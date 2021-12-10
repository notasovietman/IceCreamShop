package com.example.data.datasource

import com.example.data.model.OrderModel
import com.example.data.model.OrderPositionsModel
import io.reactivex.Completable

interface OrderDataSource {

    fun createOrder(authorization: String, order: List<OrderPositionsModel>): Completable
}