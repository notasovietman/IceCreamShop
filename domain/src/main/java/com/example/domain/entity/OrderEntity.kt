package com.example.domain.entity

data class OrderEntity(
    val id: Int,
    val user_login: String,
    val created_at: String,
    val positions: OrderPositionsEntity
)