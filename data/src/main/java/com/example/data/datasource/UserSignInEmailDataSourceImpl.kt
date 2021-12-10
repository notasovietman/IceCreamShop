package com.example.data.datasource

import com.example.data.api.UserApi
import com.example.data.model.LoginResponseModel
import com.example.data.model.UserModel
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.Response

class UserSignInEmailDataSourceImpl(private val api: UserApi): UserSignInEmailDataSource {

    override fun signIn(auth: String): Single<LoginResponseModel> = api.signIn(auth)
}