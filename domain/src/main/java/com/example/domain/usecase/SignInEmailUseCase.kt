package com.example.domain.usecase

import com.example.domain.entity.LoginResponseEntity
import com.example.domain.repository.UserSignInEmailRepository
import io.reactivex.Completable
import io.reactivex.Single

class SignInEmailUseCase(private val repository: UserSignInEmailRepository) {

    operator fun invoke(auth: String): Single<LoginResponseEntity> = repository.signIn(auth)
}