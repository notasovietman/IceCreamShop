package com.example.domain.usecase

import com.example.domain.entity.IceCreamEntity
import com.example.domain.repository.IceCreamRepository
import io.reactivex.Single

class GetAllIceCreamListUseCase(private val repository: IceCreamRepository) {

    operator fun invoke(): Single<List<IceCreamEntity>> = repository.getAllIceCreamList()
}