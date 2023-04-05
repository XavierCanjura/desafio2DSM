package com.example.desafio2.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.desafio2.databinding.ItemCartBinding
import com.example.desafio2.models.CartModel

class CartViewHolder(view: View) : ViewHolder(view) {
    private val binding = ItemCartBinding.bind(view)

    fun render(cart: CartModel, onClickListener: (CartModel) -> Unit){
        binding.tvProductName.text = cart.producto
        binding.tvPrecio.text = "$ "+cart.precio
        Glide.with(binding.ivProduct.context).load(cart.imagen).into(binding.ivProduct)
        binding.btnEliminar.setOnClickListener { onClickListener(cart) }
    }
}