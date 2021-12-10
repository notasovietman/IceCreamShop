package com.example.data.datasource

import com.example.data.api.IceCreamApi
import com.example.data.model.IceCreamModel
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class IceCreamDataSourceImpl(private val api: IceCreamApi) : IceCreamDataSource {

    override fun getAllIceCreamList(): Single<List<IceCreamModel>> = api.getIceCreamList()
    override fun getIceCreamById(id: Int): Single<IceCreamModel> = api.getIceCreamById(id)
}