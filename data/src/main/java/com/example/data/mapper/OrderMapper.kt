package com.example.data.mapper

import com.example.data.model.OrderModel
import com.example.data.model.OrderPositionsModel
import com.example.domain.entity.OrderEntity
import com.example.domain.entity.OrderPositionsEntity

fun OrderPositionsEntity.toModel(): OrderPositionsModel = OrderPositionsModel(
    icecream_id = icecream_id,
    quantity = quantity
)

fun OrderEntity.toModel(): OrderModel = OrderModel(
    id = id,
    user_login = user_login,
    created_at = created_at,
    positions = positions.toModel()
)

//fun List<OrderEntity>.toModel(): List<OrderModel> = map(OrderEntity::toModel)

fun List<OrderPositionsEntity>.toModel(): List<OrderPositionsModel> = map(OrderPositionsEntity::toModel)
