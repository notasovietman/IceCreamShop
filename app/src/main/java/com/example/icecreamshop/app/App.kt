package com.example.icecreamshop.app

import android.app.Application
import com.example.data.remote.networkModule
import com.example.icecreamshop.di.appModule
import com.example.icecreamshop.di.dataModule
import com.example.icecreamshop.di.domainModule
import com.github.terrakok.cicerone.Cicerone
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigationHolder get() = cicerone.getNavigatorHolder()

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule, networkModule))
        }
    }

    companion object {
        internal lateinit var INSTANCE: App
        //private set
    }
}

//public class Global : Application() {
//    companion object {
//        @JvmField
//        var homeAPIResponse: String =
//    }
//}