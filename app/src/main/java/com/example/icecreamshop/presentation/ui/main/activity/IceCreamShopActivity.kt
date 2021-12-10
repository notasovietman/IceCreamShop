package com.example.icecreamshop.presentation.ui.main.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.icecreamshop.R
import com.example.icecreamshop.presentation.ui.main.navigation.Screens
import com.example.icecreamshop.presentation.viewmodel.IceCreamListViewModel
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

var IS_SIGN_IN: Boolean = false

class IceCreamShopActivity : AppCompatActivity() {

    private val navigatorHolder: NavigatorHolder by inject()
    private val navigator = AppNavigator(this, R.id.container)
    lateinit var sharedPreferences: SharedPreferences
    var isRemembered: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        if (sharedPreferences.all.containsKey("TOKEN")) IS_SIGN_IN = true
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    fun buildCiceroneWithToken(): Cicerone<Router> =
        Cicerone.create().apply {
            router.newRootScreen(Screens.mainPageScreen())
        }

    fun buildCicerone(): Cicerone<Router> =
        Cicerone.create().apply {
            router.newRootScreen(Screens.enterAppScreen())
        }

}