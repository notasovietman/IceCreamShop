package com.example.icecreamshop.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.domain.usecase.GetIceCreamDetailsUseCase
import com.example.icecreamshop.presentation.ui.base.BaseViewModel
import com.example.icecreamshop.presentation.ui.main.fragment.orderList
import com.github.terrakok.cicerone.Router
import io.reactivex.internal.functions.Functions
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import kotlin.properties.Delegates

class IceCreamDetailsViewModel(
    override var router: Router,
    private val getIceCreamDetailsUseCase: GetIceCreamDetailsUseCase
) : BaseViewModel(router) {

    val iceCreamDetailsLiveData = MutableLiveData<List<String>>()
    var iceCreamId by Delegates.notNull<Int>()

    init {
        iceCreamDetailsLiveData.value = mutableListOf()
    }

    fun getIceCreamDetails() {
        getIceCreamDetailsUseCase.invoke(iceCreamId)
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.trampoline())
            .subscribe { iceCreamItem ->
                iceCreamDetailsLiveData.postValue(
                    listOf(
                        iceCreamItem.id.toString(),
                        iceCreamItem.name,
                        iceCreamItem.price.toString(),
                        iceCreamItem.weight.toString(),
                        iceCreamItem.imgURL
                    )
                )
            }.addToDisposableList()
        RxJavaPlugins.setErrorHandler(Functions.emptyConsumer())
    }

    fun addToCart(
        itemId: String,
        itemName: String,
        itemPrice: String,
        itemImage: String,
        itemQuantity: Int
    ) {
        orderList.add(mutableListOf(itemId, itemName, itemPrice, itemImage, itemQuantity.toString()))
        Log.e("Details", orderList.toString())
    }
}