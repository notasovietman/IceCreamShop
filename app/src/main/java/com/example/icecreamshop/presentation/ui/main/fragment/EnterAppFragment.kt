package com.example.icecreamshop.presentation.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.icecreamshop.R
import com.example.icecreamshop.databinding.EnterAppFragmentBinding
import com.example.icecreamshop.presentation.ui.main.navigation.Screens
import com.example.icecreamshop.presentation.ui.base.BaseFragment
import com.example.icecreamshop.presentation.viewmodel.EnterAppViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EnterAppFragment : BaseFragment<EnterAppFragmentBinding>(R.layout.enter_app_fragment) {

    private val viewModel: EnterAppViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.enterAccount.setOnClickListener {
            viewModel.navigateTo(Screens.signInScreen())
        }

        binding.registerAccount.setOnClickListener {
            viewModel.navigateTo(Screens.signUpScreen())
        }
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): EnterAppFragmentBinding = EnterAppFragmentBinding.inflate(inflater, container, false)


}