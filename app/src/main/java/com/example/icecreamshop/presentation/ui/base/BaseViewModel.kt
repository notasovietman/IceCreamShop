package com.example.icecreamshop.presentation.ui.base

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel(open var router: Router) : ViewModel() {

    private val disposableList = CompositeDisposable()

    fun navigateTo(screen: Screen) {
        router.navigateTo(screen)
    }

    fun navigateExit() {
        router.exit()
    }

    fun navigateBackTo(screen: Screen) {
        router.backTo(screen)
    }

    fun newRootScreen(screen: Screen) {
        router.newRootScreen(screen)
    }

    fun replaceScreen(screen: Screen) {
        router.replaceScreen(screen)
    }

    fun Disposable.addToDisposableList() {
        disposableList.add(this)
    }

    override fun onCleared() {
        disposableList.dispose()
        super.onCleared()
    }

}