package com.example.icecreamshop.presentation.ui.main.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entity.OrderEntity
import com.example.icecreamshop.R
import com.example.icecreamshop.databinding.CartListFragmentBinding
import com.example.icecreamshop.presentation.ui.base.BaseFragment
import com.example.icecreamshop.presentation.ui.main.adapter.CartListAdapter
import com.example.icecreamshop.presentation.ui.main.navigation.Screens
import com.example.icecreamshop.presentation.viewmodel.CartListViewModel
import com.example.icecreamshop.presentation.viewmodel.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartListFragment() : BaseFragment<CartListFragmentBinding>(R.layout.cart_list_fragment) {

    private val viewModel: CartListViewModel by viewModel()
    private lateinit var adapter: CartListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayoutManager()
        setObservers()
        binding.backButton.setOnClickListener {
            viewModel.navigateBackTo(Screens.mainPageScreen())
        }
    }

    private fun setObservers() {
        viewModel.orderListLiveData.observe(viewLifecycleOwner) {
            adapter = CartListAdapter(it)
            binding.basketListRecyclerView.adapter = adapter
            setListeners()
        }
        viewModel.orderStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                CartListViewModel.OPERATION_SUCCESS -> {
                    Toast.makeText(context, "Заказ успешно создан", Toast.LENGTH_SHORT).show()
                }
                CartListViewModel.OPERATION_FAILED -> {
                    Toast.makeText(context, "Ошибка создания заказа", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setListeners() {
        binding.createOrderBtn.setOnClickListener {
            viewModel.createOrder()
            if (viewModel.orderStateLiveData.value == CartListViewModel.OPERATION_SUCCESS) {
                adapter.clearRecycler()
                adapter.notifyDataSetChanged()
                orderList.clear()
                viewModel.finalOrderList.clear()
            }
        }
        binding.deleteAllBtn.setOnClickListener {
            adapter.clearRecycler()
            adapter.notifyDataSetChanged()
            orderList.clear()
        }
    }

    private fun setLayoutManager() {
        binding.basketListRecyclerView.layoutManager =
            LinearLayoutManager(binding.basketListRecyclerView.context)
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CartListFragmentBinding = CartListFragmentBinding.inflate(inflater, container, false)
}