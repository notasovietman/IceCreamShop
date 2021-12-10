package com.example.data.datasource

import com.example.data.model.LoginResponseModel
import io.reactivex.Completable
import io.reactivex.Single

interface UserSignInEmailDataSource {

    fun signIn(auth: String): Single<LoginResponseModel>
}