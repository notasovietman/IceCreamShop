package com.example.icecreamshop.presentation.ui.main.navigation

import android.content.Context
import android.content.SharedPreferences
import com.example.icecreamshop.app.App
import com.example.icecreamshop.presentation.ui.main.activity.IS_SIGN_IN
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

fun buildCicerone(): Cicerone<Router> =
    Cicerone.create().apply {
//        if (IS_SIGN_IN)
//            router.newRootScreen(Screens.mainPageScreen())
//        else router.newRootScreen(Screens.enterAppScreen())
        router.newRootScreen(Screens.enterAppScreen())
    }
