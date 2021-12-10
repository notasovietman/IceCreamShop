package com.example.domain.repository

import com.example.domain.entity.LoginResponseEntity
import io.reactivex.Completable
import io.reactivex.Single

interface UserSignInEmailRepository {

    fun signIn(auth: String): Single<LoginResponseEntity>
}