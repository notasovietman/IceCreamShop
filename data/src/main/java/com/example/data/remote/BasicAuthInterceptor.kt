package com.example.data.remote

import com.example.data.model.UserModel
import com.google.gson.GsonBuilder
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BasicAuthInterceptor(private val userModel: UserModel) : Interceptor {

    private lateinit var credentials: String

    override fun intercept(chain: Interceptor.Chain): Response {
        credentials = Credentials.basic(username = userModel.login, password = userModel.password)
        var request = chain.request()
        request = request.newBuilder().header("Authorization", credentials).build()
        return chain.proceed(request)
    }
}
