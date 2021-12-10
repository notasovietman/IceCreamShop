package com.example.domain.repository

import com.example.domain.entity.IceCreamEntity
import io.reactivex.Single

interface IceCreamRepository {

    fun getAllIceCreamList() : Single<List<IceCreamEntity>>

    fun getIceCreamById(id: Int): Single<IceCreamEntity>
}