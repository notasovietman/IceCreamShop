package com.example.data.mapper

import com.example.data.model.IceCreamModel
import com.example.domain.entity.IceCreamEntity

fun IceCreamModel.toEntity(): IceCreamEntity = IceCreamEntity(
    id = id,
    name = name,
    price = price,
    weight = weight,
    imgURL = imgURL
)

fun List<IceCreamModel>.toEntity(): List<IceCreamEntity> = map(IceCreamModel::toEntity)
