package com.example.data.mapper

import com.example.data.model.LoginResponseModel
import com.example.domain.entity.LoginResponseEntity

fun LoginResponseModel.toEntity(): LoginResponseEntity = LoginResponseEntity(
    token = token
)

fun LoginResponseEntity.toModel(): LoginResponseModel = LoginResponseModel(
    token = token
)