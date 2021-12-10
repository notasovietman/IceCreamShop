package com.example.icecreamshop.di

import com.example.domain.usecase.*
import org.koin.dsl.module

val domainModule = module {
    factory { GetAllIceCreamListUseCase(get()) }
    factory { SignUpEmailUseCase(get()) }
    factory { SignInEmailUseCase(get()) }
    factory { GetIceCreamDetailsUseCase(get()) }
    factory { CreateOrderUseCase(get()) }
}