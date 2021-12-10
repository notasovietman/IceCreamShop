package com.example.icecreamshop.presentation.ui.main.navigation

import androidx.fragment.app.Fragment
import com.example.icecreamshop.presentation.ui.main.fragment.*
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun enterAppScreen() = FragmentScreen { EnterAppFragment() }
    fun signInScreen() = FragmentScreen { SignInFragment() }
    fun signUpScreen() = FragmentScreen { SignUpFragment() }
    fun mainPageScreen() = FragmentScreen { MainPageFragment() }
    fun cartListScreen() = FragmentScreen { CartListFragment() }
}