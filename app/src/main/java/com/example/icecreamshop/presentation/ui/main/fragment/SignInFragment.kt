package com.example.icecreamshop.presentation.ui.main.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.icecreamshop.R
import com.example.icecreamshop.databinding.SignInFragmentBinding
import com.example.icecreamshop.presentation.ui.base.BaseFragment
import com.example.icecreamshop.presentation.ui.main.navigation.Screens
import com.example.icecreamshop.presentation.viewmodel.SignInViewModel
import com.example.icecreamshop.presentation.viewmodel.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignInFragment: BaseFragment<SignInFragmentBinding>(R.layout.sign_in_fragment) {

    private val viewModel: SignInViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
    }

    private fun setListeners() {
        with(binding) {
            signInBtn.setOnClickListener {
                viewModel.emailLiveData.value = usernameEdit.text.toString()
                viewModel.passwordLiveData.value = passwordEdit.text.toString()
                viewModel.fieldsCheck()
            }
            noAccount.setOnClickListener {
                viewModel.replaceScreen(Screens.signUpScreen())
            }
            usernameEdit.doOnTextChanged { text, _, _, _ ->
                with(viewModel) {
                    emailLiveData.value = text.toString()
                }
            }
            passwordEdit.doOnTextChanged { text, _, _, _ ->
                with(viewModel) {
                    passwordLiveData.value = text.toString()
                }
            }
        }
    }

    private fun setObservers() {
        viewModel.signInStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                SignUpViewModel.OPERATION_SUCCESS -> {
//                    val pref = activity?.getPreferences(Context.MODE_PRIVATE)
//                    val editor = pref?.edit()
//                    editor?.putString("TOKEN", viewModel.userToken)?.apply()
                    USER_INFO.login = viewModel.emailLiveData.value!!
                    USER_INFO.password = viewModel.passwordLiveData.value!!
                    viewModel.navigateTo(Screens.mainPageScreen())
                }
                SignUpViewModel.OPERATION_FAILED -> {
                    Toast.makeText(context, "Не удалось авторизироваться", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SignInFragmentBinding = SignInFragmentBinding.inflate(inflater, container, false)

}