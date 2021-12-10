package com.example.icecreamshop.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.entity.UserEntity
import com.example.domain.usecase.SignUpEmailUseCase
import com.example.icecreamshop.presentation.ui.base.BaseViewModel
import com.github.terrakok.cicerone.Router
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.functions.Functions
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import java.util.*

const val EMPTY_EMAIL_ERROR = "Укажите адрес электронной почты"
const val EMPTY_PASSWORD_ERROR = "Укажите пароль"

class SignUpViewModel(
    override var router: Router,
    private val userSignUpEmailUseCase: SignUpEmailUseCase
) :
    BaseViewModel(router) {

    companion object {
        const val OPERATION_SUCCESS = 0
        const val OPERATION_FAILED = 1
    }

    val emailLiveData = MutableLiveData<String>()
    val passwordLiveData = MutableLiveData<String>()
    val emailErrorLiveData = MutableLiveData<String>()
    val passwordErrorLiveData = MutableLiveData<String>()
    val signUpStateLiveData = MutableLiveData<Int>()

    fun fieldsCheck() {
        signUp()
    }

    private fun signUp() {
        userSignUpEmailUseCase.invoke(
            UserEntity(
                login = emailLiveData.value!!,
                password = passwordLiveData.value!!
            )
        )
            .subscribeOn(Schedulers.newThread()).observeOn(Schedulers.trampoline())
            .subscribe({
                val authPayload = "${emailLiveData.value}:${passwordLiveData.value}"
                val data = authPayload.toByteArray()
                val base64 = Base64.getEncoder().encodeToString(data)
                BASE = "Basic $base64".trim()
                signUpStateLiveData.postValue(OPERATION_SUCCESS)
            }, {
                signUpStateLiveData.postValue(OPERATION_FAILED)
            })
            .addToDisposableList()

        RxJavaPlugins.setErrorHandler(Functions.emptyConsumer())
    }

}