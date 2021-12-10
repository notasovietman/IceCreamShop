package com.example.icecreamshop.presentation.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.Resource
import com.example.icecreamshop.databinding.MainPageListItemFragmentBinding
import java.util.*
import kotlin.collections.ArrayList

class IceCreamListAdapter(private val list: MutableList<List<String>>) :
    RecyclerView.Adapter<IceCreamListAdapter.IceCreamListViewHolder>() {

    private lateinit var listener: OnItemClickListener

    inner class IceCreamListViewHolder(val binding: MainPageListItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemClick(position: Int, holder: IceCreamListViewHolder)
    }

    fun setOnItemClickListener(itemListener: OnItemClickListener) {
        listener = itemListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IceCreamListAdapter.IceCreamListViewHolder {
        return IceCreamListViewHolder(
            MainPageListItemFragmentBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: IceCreamListViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.iceCreamName.text = get(1)
                binding.priceBtn.text = get(2) + "₽"
                binding.iceCreamWeight.text = get(3) + " г"
                Glide.with(binding.imageIceCream)
                    .load(get(4))
                    .into(binding.imageIceCream)
            }
            binding.priceBtn.setOnClickListener {
                listener.onItemClick(position, holder)
            }
        }
    }

    override fun getItemCount(): Int = list.size
}