package com.example.domain.repository

import com.example.domain.entity.OrderEntity
import com.example.domain.entity.OrderPositionsEntity
import io.reactivex.Completable

interface OrderRepository {

    fun createOrder(authorization: String, order: List<OrderPositionsEntity>): Completable
}