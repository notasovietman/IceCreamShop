package com.example.icecreamshop.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.domain.entity.OrderPositionsEntity
import com.example.domain.usecase.CreateOrderUseCase
import com.example.icecreamshop.presentation.ui.base.BaseViewModel
import com.example.icecreamshop.presentation.ui.main.fragment.orderList
import com.github.terrakok.cicerone.Router
import io.reactivex.internal.functions.Functions
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers

class CartListViewModel(
    override var router: Router,
    private val createOrderUseCase: CreateOrderUseCase
) :
    BaseViewModel(router) {

    companion object {
        const val OPERATION_SUCCESS = 0
        const val OPERATION_FAILED = 1
    }

    val orderListLiveData = MutableLiveData<MutableList<List<String>>>()
    val orderStateLiveData = MutableLiveData<Int>()
    val finalOrderList: MutableList<OrderPositionsEntity> = mutableListOf()

    init {
        orderListLiveData.value = mutableListOf()
        addToCart()
    }

    fun createOrder() {
        finalOrderList.clear()
        orderList.clear()
        for (item in orderListLiveData.value!!) {
            finalOrderList.add(
                 OrderPositionsEntity(
                     icecream_id = item[0].toInt(),
                     quantity = item[4].toInt()
                 )
            )
        }
        createOrderUseCase.invoke(BASE, finalOrderList)
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.trampoline())
            .subscribe({
                Log.e("ORDER_VM", orderListLiveData.value.toString())
                orderStateLiveData.postValue(OPERATION_SUCCESS)
            }, {
                Log.e("ORDER_VM", orderListLiveData.toString())
                orderStateLiveData.postValue(OPERATION_FAILED)
            })
            .addToDisposableList()
        RxJavaPlugins.setErrorHandler(Functions.emptyConsumer())
    }

    private fun addToCart() {
        for (item in orderList) {
            orderListLiveData.value?.add(item)
            orderListLiveData.value = orderListLiveData.value
            Log.e("ORDER_ADD", orderListLiveData.value.toString())
        }
    }
}

