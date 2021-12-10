package com.example.domain.usecase

import com.example.domain.entity.IceCreamEntity
import com.example.domain.repository.IceCreamRepository
import io.reactivex.Single

class GetIceCreamDetailsUseCase(private val repository: IceCreamRepository) {

    operator fun invoke(id: Int): Single<IceCreamEntity> = repository.getIceCreamById(id)
}