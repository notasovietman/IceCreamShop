package com.example.icecreamshop.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.GetAllIceCreamListUseCase
import com.example.icecreamshop.presentation.ui.base.BaseViewModel
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.functions.Functions
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers

class IceCreamListViewModel(
    override var router: Router,
    private val getAllIceCreamListUseCase: GetAllIceCreamListUseCase
) :
    BaseViewModel(router) {

    val iceCreamListLiveData = MutableLiveData<MutableList<List<String>>>()

    init {
        iceCreamListLiveData.value = mutableListOf()
        getIceCreamList()
    }

    private fun getIceCreamList() {
        getAllIceCreamListUseCase.invoke()
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.trampoline())
            .subscribe { iceCreamList ->
                iceCreamListLiveData.postValue(iceCreamList.map {
                    listOf(
                        it.id.toString(),
                        it.name,
                        it.price.toString(),
                        it.weight.toString(),
                        it.imgURL
                    )
                }.toMutableList())
            }.addToDisposableList()

        RxJavaPlugins.setErrorHandler(Functions.emptyConsumer())
    }
}
