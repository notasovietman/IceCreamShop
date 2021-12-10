package com.example.icecreamshop.presentation.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.icecreamshop.databinding.CartListItemFragmentBinding
import com.example.icecreamshop.databinding.MainPageListItemFragmentBinding
import com.example.icecreamshop.presentation.ui.main.fragment.orderList

class CartListAdapter(private val list: MutableList<List<String>>) :
    RecyclerView.Adapter<CartListAdapter.CartListViewHolder>() {

    inner class CartListViewHolder(val binding: CartListItemFragmentBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartListAdapter.CartListViewHolder {
        return CartListViewHolder(
            CartListItemFragmentBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CartListViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.iceCreamName.text = get(1)
                binding.iceCreamPrice.text = get(2)
                binding.currentQuantity.text = get(4)
                Glide.with(binding.imageIceCream)
                    .load(get(3))
                    .into(binding.imageIceCream)
                binding.removeBtn.setOnClickListener {
                    list.removeAt(position)
                    orderList.removeAt(position)
                    notifyItemRemoved(position)
                }
                binding.plusOneBtn.setOnClickListener {
                    var price = binding.iceCreamPrice.text.toString().toDouble()
                    var quantity = binding.currentQuantity.text.toString().toInt()
                    price /= quantity
                    quantity++
                    price *= quantity
                    binding.currentQuantity.text = quantity.toString()
                    //binding.iceCreamPrice.text = String.format("%.2f", price).replace(',', '.')
                    binding.iceCreamPrice.text = price.toString()
                    orderList[position][2] = price.toString()
                    orderList[position][4] = quantity.toString()
                }
                binding.minusOneBtn.setOnClickListener {
                    var price = binding.iceCreamPrice.text.toString().toDouble()
                    var quantity = binding.currentQuantity.text.toString().toInt()
                    if (quantity > 0) {
                        price /= quantity
                        quantity--
                        price *= quantity
                        binding.currentQuantity.text = quantity.toString()
                        //binding.iceCreamPrice.text = String.format("%.2f", price).replace(',', '.')
                        binding.iceCreamPrice.text = price.toString()
                        orderList[position][2] = price.toString()
                        orderList[position][4] = quantity.toString()
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = list.size

    fun clearRecycler() {
        list.clear()
    }

}