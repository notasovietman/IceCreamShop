package com.example.data.datasource

import com.example.data.api.OrderApi
import com.example.data.model.OrderModel
import com.example.data.model.OrderPositionsModel
import io.reactivex.Completable

class OrderDataSourceImpl(private val api: OrderApi) : OrderDataSource {

    override fun createOrder(authorization: String, order: List<OrderPositionsModel>): Completable =
        api.createOrder(authorization, order)
}