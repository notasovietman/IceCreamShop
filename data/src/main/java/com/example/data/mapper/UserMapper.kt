package com.example.data.mapper

import com.example.data.model.UserModel
import com.example.domain.entity.UserEntity

fun UserEntity.toModel(): UserModel = UserModel(
    login = login,
    password = password
)