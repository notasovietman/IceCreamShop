package com.example.data.repository

import com.example.data.datasource.IceCreamDataSource
import com.example.data.mapper.toEntity
import com.example.data.model.IceCreamModel
import com.example.domain.entity.IceCreamEntity
import com.example.domain.repository.IceCreamRepository
import io.reactivex.Single

class IceCreamRepositoryImpl(private val dataSource: IceCreamDataSource) : IceCreamRepository {

    override fun getAllIceCreamList(): Single<List<IceCreamEntity>> =
        dataSource.getAllIceCreamList().map(List<IceCreamModel>::toEntity)

    override fun getIceCreamById(id: Int): Single<IceCreamEntity> =
        dataSource.getIceCreamById(id).map(IceCreamModel::toEntity)
}