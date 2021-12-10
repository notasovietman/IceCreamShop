package com.example.domain.repository

import com.example.domain.entity.UserEntity
import io.reactivex.Completable

interface UserSignUpEmailRepository {

    fun signUp(userEntity: UserEntity): Completable
}