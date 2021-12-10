package com.example.data.repository

import com.example.data.datasource.UserSignInEmailDataSource
import com.example.data.mapper.toEntity
import com.example.data.mapper.toModel
import com.example.data.model.LoginResponseModel
import com.example.domain.entity.LoginResponseEntity
import com.example.domain.entity.UserEntity
import com.example.domain.repository.UserSignInEmailRepository
import io.reactivex.Completable
import io.reactivex.Single

class UserSignInEmailRepositoryImpl(private val dataSource: UserSignInEmailDataSource) :
    UserSignInEmailRepository {

    override fun signIn(auth: String): Single<LoginResponseEntity> =
        dataSource.signIn(auth).map(LoginResponseModel::toEntity)

}