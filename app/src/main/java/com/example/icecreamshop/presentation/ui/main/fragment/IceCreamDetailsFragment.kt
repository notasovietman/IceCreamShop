package com.example.icecreamshop.presentation.ui.main.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.icecreamshop.R
import com.example.icecreamshop.databinding.IceCreamDetailsFragmentBinding
import com.example.icecreamshop.presentation.ui.base.BaseCustomDialog
import com.example.icecreamshop.presentation.viewmodel.IceCreamDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class IceCreamDetailsFragment(private val iceCreamId: Int) :
    BaseCustomDialog<IceCreamDetailsFragmentBinding, IceCreamDetailsFragment.ShowInfoDialogFragmentListener>(
        R.layout.ice_cream_details_fragment
    ) {

    interface ShowInfoDialogFragmentListener : BaseCustomDialog.BaseDialogListener {
        fun onDialogCloseResult(requestCode: Int)
    }

    private val viewModel: IceCreamDetailsViewModel by viewModel()
    private var quantity: Int = 1
    private var itemIdString: String = "0"
    private var price: Double = 0.0
    private var priceString: String = "0.0"
    private var imageString: String = "null"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val width = (resources.displayMetrics.widthPixels * 0.9).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.35).toInt()
        dialog?.window?.setLayout(width, height)
        super.onViewCreated(view, savedInstanceState)
        viewModel.iceCreamId = iceCreamId
        viewModel.getIceCreamDetails()
        setObservers()
        itemInfo()
        addToCart()
    }

    private fun setObservers() {
        viewModel.iceCreamDetailsLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                val itemList = viewModel.iceCreamDetailsLiveData.value!!
                itemIdString = itemList.getOrNull(0).toString()
                iceCreamName.text = itemList.getOrNull(1)
                iceCreamPrice.text = itemList.getOrNull(2)
                priceString = iceCreamPrice.text.toString()
                iceCreamWeight.text = itemList.getOrNull(3) + " г"
                imageString = itemList.getOrElse(4) { "nothing" }
                Glide.with(imageIceCream)
                    .load(itemList.getOrNull(4))
                    .into(imageIceCream)
            }
        }
    }

    private fun itemInfo() {
        with(binding) {
            quantity = currentQuantity.text.toString().toInt()
            price = priceString.toDouble()
            minusOneBtn.setOnClickListener {
                if (quantity > 0) {
                    quantity--
                    price = priceString.toDouble() * quantity
                    currentQuantity.text = quantity.toString()
                    iceCreamPrice.text = price.toString() + "₽"
                }
            }
            plusOneBtn.setOnClickListener {
                quantity++
                price = priceString.toDouble() * quantity
                currentQuantity.text = quantity.toString()
                iceCreamPrice.text = price.toString() + "₽"
            }
        }
    }

    private fun addToCart() {
        with(binding) {
            addToCartBtn.setOnClickListener {
                viewModel.addToCart(
                    itemIdString,
                    iceCreamName.text.toString(),
                    iceCreamPrice.text.toString()
                        .substring(0, iceCreamPrice.text.toString().length - 1),
                    imageString,
                    quantity
                )
                dialog?.dismiss()
                Toast.makeText(context,"Товар добавлен в корзину", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): IceCreamDetailsFragmentBinding =
        IceCreamDetailsFragmentBinding.inflate(inflater, container, false)
}
