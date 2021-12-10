package com.example.icecreamshop.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding>(private val layoutId: Int) : Fragment() {

    private var _binding: T? = null
    val binding: T get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getBinding(inflater, container)
        return binding.root
    }

    abstract fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): T

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}