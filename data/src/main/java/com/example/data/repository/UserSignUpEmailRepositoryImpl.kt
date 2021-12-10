package com.example.data.repository

import com.example.data.datasource.UserSignUpEmailDataSource
import com.example.data.mapper.toModel
import com.example.domain.entity.UserEntity
import com.example.domain.repository.UserSignUpEmailRepository
import io.reactivex.Completable

class UserSignUpEmailRepositoryImpl(private val dataSource: UserSignUpEmailDataSource) :
    UserSignUpEmailRepository {

    override fun signUp(userEntity: UserEntity): Completable = dataSource.signUp(userEntity.toModel())
}