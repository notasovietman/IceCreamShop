package com.example.icecreamshop.di

import com.example.icecreamshop.presentation.ui.main.navigation.buildCicerone
import com.example.icecreamshop.presentation.viewmodel.*
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { buildCicerone() }
    single { get<Cicerone<Router>>().router }
    single { get<Cicerone<Router>>().getNavigatorHolder() }

    viewModel { EnterAppViewModel(router = get()) }
    viewModel { SignInViewModel(router = get(), userSignInEmailUseCase = get()) }
    viewModel { SignUpViewModel(router = get(), userSignUpEmailUseCase = get()) }
    viewModel { IceCreamListViewModel(router = get(), getAllIceCreamListUseCase = get()) }
    viewModel {
        IceCreamDetailsViewModel(
            router = get(),
            getIceCreamDetailsUseCase = get()

        )
    }
    viewModel { CartListViewModel(router = get(), createOrderUseCase = get())}
}