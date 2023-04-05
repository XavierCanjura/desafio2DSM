package com.example.desafio2.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.desafio2.R
import com.example.desafio2.models.ProductModel

class ProductAdapter(private var productList: List<ProductModel>, private var onClickListener: (ProductModel) -> Unit): Adapter<ProductViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder { // Pinta los atributos
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(layoutInflater.inflate(R.layout.item_product, parent, false))
    }

    // Recorre cada uno de los items de la lista y llama el render del viewHolder
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.render(product, onClickListener)
    }

    override fun getItemCount(): Int = productList.size

    fun setProductList(newProductList: List<ProductModel>){
        this.productList = newProductList
        notifyDataSetChanged()
    }
}