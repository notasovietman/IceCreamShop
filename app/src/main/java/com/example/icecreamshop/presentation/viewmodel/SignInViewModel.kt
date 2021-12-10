package com.example.icecreamshop.presentation.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.example.data.model.UserModel
import com.example.data.remote.BasicAuthInterceptor
import com.example.data.remote.networkModule
import com.example.domain.usecase.SignInEmailUseCase
import com.example.icecreamshop.presentation.ui.base.BaseViewModel
import com.github.terrakok.cicerone.Router
import io.reactivex.internal.functions.Functions
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import java.lang.reflect.InvocationTargetException
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.*

const val EMPTY_EMAIL = "Заполните логин"
const val EMPTY_PASSWORD = "Заполните пароль"
var BASE = "Base"

class SignInViewModel(
    override var router: Router,
    private val userSignInEmailUseCase: SignInEmailUseCase
) : BaseViewModel(router) {

    companion object {
        const val OPERATION_SUCCESS = 0
        const val OPERATION_FAILED = 1
    }

    val emailLiveData = MutableLiveData<String>()
    val passwordLiveData = MutableLiveData<String>()
    val signInStateLiveData = MutableLiveData<Int>()
    var userToken: String = "token"

    fun fieldsCheck() {
        signIn()
    }

    private fun signIn() {
        val authPayload = "${emailLiveData.value}:${passwordLiveData.value}"
        val data = authPayload.toByteArray()
        val base64 = Base64.getEncoder().encodeToString(data)
        BASE = "Basic $base64".trim()

        try {
            networkModule.single(named((1..100).random().toString())) {
                OkHttpClient.Builder()
                    .addInterceptor(
                        BasicAuthInterceptor(
                            userModel = UserModel(
                                login = emailLiveData.value!!,
                                password = passwordLiveData.value!!
                            )
                        )
                    )
                    .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
        } catch (e: InvocationTargetException) {
            e.cause?.printStackTrace()
        }
        userSignInEmailUseCase.invoke(auth = "Basic $base64".trim())
            .subscribeOn(Schedulers.newThread()).observeOn(Schedulers.trampoline())
            .subscribe({ response ->
                userToken = response.token
                signInStateLiveData.postValue(SignUpViewModel.OPERATION_SUCCESS)
            }, {
                signInStateLiveData.postValue(SignUpViewModel.OPERATION_FAILED)
            })
            .addToDisposableList()

        RxJavaPlugins.setErrorHandler(Functions.emptyConsumer())
    }

}