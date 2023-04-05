package com.example.desafio2.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.desafio2.databinding.ItemProductBinding
import com.example.desafio2.models.ProductModel

class ProductViewHolder(view: View): ViewHolder(view) {
    private val binding = ItemProductBinding.bind(view)

    fun render(product: ProductModel, onClickListener: (ProductModel) -> Unit){
        binding.tvProductName.text = product.nombre
        binding.tvDescripcion.text = product.indicaciones
        binding.tvContraIndicaciones.text = product.contraIndicaciones
        binding.tvPrecio.text = "$ ${product.precio}"
        Glide.with(binding.ivProductImage.context).load(product.imagen).into(binding.ivProductImage)
        binding.btAddCart.setOnClickListener { onClickListener(product) }
    }
}