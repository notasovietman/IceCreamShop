package com.example.data.datasource

import com.example.data.api.UserApi
import com.example.data.model.UserModel
import io.reactivex.Completable

class UserSignUpEmailDataSourceImpl(private val api: UserApi): UserSignUpEmailDataSource {

    override fun signUp(userModel: UserModel): Completable = api.signUp(userModel)
}