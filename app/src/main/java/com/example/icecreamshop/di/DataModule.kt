package com.example.icecreamshop.di

import com.example.data.api.IceCreamApi
import com.example.data.api.OrderApi
import com.example.data.api.UserApi
import com.example.data.datasource.*
import com.example.data.repository.IceCreamRepositoryImpl
import com.example.data.repository.OrderRepositoryImpl
import com.example.data.repository.UserSignInEmailRepositoryImpl
import com.example.data.repository.UserSignUpEmailRepositoryImpl
import com.example.domain.repository.IceCreamRepository
import com.example.domain.repository.OrderRepository
import com.example.domain.repository.UserSignInEmailRepository
import com.example.domain.repository.UserSignUpEmailRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    factory { get<Retrofit>().create(IceCreamApi::class.java) }
    factory<IceCreamDataSource> { IceCreamDataSourceImpl(get()) }
    factory<IceCreamRepository> { IceCreamRepositoryImpl(get()) }

    factory { get<Retrofit>().create(UserApi::class.java) }
    factory<UserSignUpEmailDataSource> { UserSignUpEmailDataSourceImpl(get()) }
    factory<UserSignUpEmailRepository> { UserSignUpEmailRepositoryImpl(get()) }
    factory<UserSignInEmailDataSource> { UserSignInEmailDataSourceImpl(get()) }
    factory<UserSignInEmailRepository> { UserSignInEmailRepositoryImpl(get()) }

    factory { get<Retrofit>().create(OrderApi::class.java) }
    factory<OrderDataSource> { OrderDataSourceImpl(get()) }
    factory<OrderRepository> { OrderRepositoryImpl(get()) }
}