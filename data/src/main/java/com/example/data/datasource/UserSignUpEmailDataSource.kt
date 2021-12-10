package com.example.data.datasource

import com.example.data.model.UserModel
import io.reactivex.Completable

interface UserSignUpEmailDataSource {

    fun signUp(userModel: UserModel): Completable
}