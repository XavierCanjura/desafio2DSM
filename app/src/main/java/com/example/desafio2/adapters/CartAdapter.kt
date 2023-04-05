package com.example.desafio2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.desafio2.R
import com.example.desafio2.models.CartModel

class CartAdapter(private var cartList: List<CartModel>, private val onClickListener: (CartModel) -> Unit) : Adapter<CartViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder { // Pinta los atributos
        val layoutInflater = LayoutInflater.from(parent.context)
        return CartViewHolder(layoutInflater.inflate(R.layout.item_cart, parent, false))
    }

    // Recorre cada uno de los items de la lista y llama el render del viewHolder
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cart = cartList[position]
        holder.render(cart, onClickListener)
    }

    override fun getItemCount(): Int = cartList.size

    fun setCartList(newCartList: List<CartModel>){
        this.cartList = newCartList
        notifyDataSetChanged()
    }
}