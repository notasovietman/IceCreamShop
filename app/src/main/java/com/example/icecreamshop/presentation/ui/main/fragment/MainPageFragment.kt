package com.example.icecreamshop.presentation.ui.main.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.icecreamshop.R
import com.example.icecreamshop.databinding.MainPageListFragmentBinding
import com.example.icecreamshop.presentation.ui.base.BaseFragment
import com.example.icecreamshop.presentation.ui.base.showCustomDialog
import com.example.icecreamshop.presentation.ui.main.adapter.IceCreamListAdapter
import com.example.icecreamshop.presentation.ui.main.navigation.Screens
import com.example.icecreamshop.presentation.viewmodel.IceCreamListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


val orderList:MutableList<MutableList<String>> = mutableListOf()

class MainPageFragment :
    BaseFragment<MainPageListFragmentBinding>(R.layout.main_page_list_fragment) {

    private val viewModel: IceCreamListViewModel by viewModel()
    private lateinit var adapter: IceCreamListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayoutManager()
        setObservers()

        binding.backButton.setOnClickListener {
            val settings = activity?.getPreferences(Context.MODE_PRIVATE)
            settings?.edit()?.clear()?.apply()
            viewModel.navigateTo(Screens.enterAppScreen())
        }
    }

    private fun setObservers() {
        viewModel.iceCreamListLiveData.observe(viewLifecycleOwner) {
            adapter = IceCreamListAdapter(it)
            binding.basketListRecyclerView.adapter = adapter
            setListener()
        }
    }

    private fun setListener() {
        adapter.setOnItemClickListener(object : IceCreamListAdapter.OnItemClickListener {
            override fun onItemClick(
                position: Int,
                holder: IceCreamListAdapter.IceCreamListViewHolder
            ) {
                val iceCreamId = viewModel.iceCreamListLiveData.value?.get(position)?.get(0)
                showCustomDialog(IceCreamDetailsFragment(iceCreamId = iceCreamId!!.toInt()))
            }
        })
        binding.basketListBtn.setOnClickListener {
            viewModel.navigateTo(Screens.cartListScreen())
        }
    }

    private fun setLayoutManager() {
        binding.basketListRecyclerView.layoutManager =
            GridLayoutManager(binding.basketListRecyclerView.context, 2)
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MainPageListFragmentBinding = MainPageListFragmentBinding.inflate(inflater, container, false)
}