package com.example.icecreamshop.presentation.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.data.model.UserModel
import com.example.icecreamshop.R
import com.example.icecreamshop.databinding.SignUpFragmentBinding
import com.example.icecreamshop.presentation.ui.base.BaseFragment
import com.example.icecreamshop.presentation.ui.main.navigation.Screens
import com.example.icecreamshop.presentation.viewmodel.SignUpViewModel
import com.example.icecreamshop.presentation.viewmodel.SignUpViewModel.Companion.OPERATION_FAILED
import com.example.icecreamshop.presentation.viewmodel.SignUpViewModel.Companion.OPERATION_SUCCESS
import com.github.terrakok.cicerone.Screen
import org.koin.androidx.viewmodel.ext.android.viewModel

var USER_INFO = UserModel("", "")

class SignUpFragment: BaseFragment<SignUpFragmentBinding>(R.layout.sign_up_fragment) {

    private val viewModel: SignUpViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
    }

    private fun setListeners() {
        with(binding) {
            signUpBtn.setOnClickListener {
                viewModel.emailLiveData.value = usernameEdit.text.toString()
                viewModel.passwordLiveData.value = passwordEdit.text.toString()
                viewModel.fieldsCheck()
            }
            haveAccount.setOnClickListener {
                viewModel.replaceScreen(Screens.signInScreen())
            }
            usernameEdit.doOnTextChanged { text, _, _, _ ->
                with(viewModel) {
                    emailLiveData.value = text.toString()
                    emailErrorLiveData.value = null
                }
            }
            passwordEdit.doOnTextChanged { text, _, _, _ ->
                with(viewModel) {
                    passwordLiveData.value = text.toString()
                    passwordErrorLiveData.value = null
                }
            }
        }
    }

    private fun setObservers() {
        viewModel.emailErrorLiveData.observe(viewLifecycleOwner) {
            binding.usernameTextField.error = it
        }
        viewModel.passwordErrorLiveData.observe(viewLifecycleOwner) {
            binding.passwordTextField.error = it
        }
        viewModel.signUpStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                OPERATION_SUCCESS -> {
                    USER_INFO.login = viewModel.emailLiveData.value!!
                    USER_INFO.password = viewModel.passwordLiveData.value!!
                    viewModel.navigateTo(Screens.mainPageScreen())
                }
                OPERATION_FAILED -> {
                    Toast.makeText(context,"Не удалось зарегистрироваться", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): SignUpFragmentBinding  = SignUpFragmentBinding.inflate(inflater, container, false)
}