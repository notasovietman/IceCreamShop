package com.example.data.api

import android.telecom.Call
import com.example.data.model.LoginResponseModel
import com.example.data.model.UserModel
import io.reactivex.Completable
import io.reactivex.Single
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.*

interface UserApi {

    @POST("/api/user/new")
    fun signUp(@Body userModel: UserModel): Completable

    @POST("/api/user/login")
    fun signIn(@Header("Authorization") authorization: String) : Single<LoginResponseModel>
}