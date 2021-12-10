package com.example.domain.usecase

import com.example.domain.entity.OrderEntity
import com.example.domain.entity.OrderPositionsEntity
import com.example.domain.repository.OrderRepository
import io.reactivex.Completable

class CreateOrderUseCase(private val repository: OrderRepository) {

    operator fun invoke(authorization: String, order: List<OrderPositionsEntity>): Completable =
        repository.createOrder(authorization, order)
}