package com.example.domain.usecase

import com.example.domain.entity.UserEntity
import com.example.domain.repository.UserSignUpEmailRepository
import io.reactivex.Completable

class SignUpEmailUseCase(private val repository: UserSignUpEmailRepository) {

    operator fun invoke(userEntity: UserEntity): Completable = repository.signUp(userEntity)
}