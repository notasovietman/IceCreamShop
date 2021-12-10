package com.example.data.datasource

import com.example.data.model.IceCreamModel
import io.reactivex.Completable
import io.reactivex.Single

interface IceCreamDataSource {

    fun getAllIceCreamList(): Single<List<IceCreamModel>>

    fun getIceCreamById(id: Int): Single<IceCreamModel>
}