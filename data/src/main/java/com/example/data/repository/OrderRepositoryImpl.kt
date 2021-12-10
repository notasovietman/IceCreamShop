package com.example.data.repository

import com.example.data.datasource.OrderDataSource
import com.example.data.mapper.toModel
import com.example.data.model.OrderPositionsModel
import com.example.domain.entity.OrderEntity
import com.example.domain.entity.OrderPositionsEntity
import com.example.domain.repository.OrderRepository
import io.reactivex.Completable


class OrderRepositoryImpl(private val dataSource: OrderDataSource) : OrderRepository {

    override fun createOrder(
        authorization: String,
        order: List<OrderPositionsEntity>
    ): Completable =
        dataSource.createOrder(authorization = authorization, order = order.toModel())

}