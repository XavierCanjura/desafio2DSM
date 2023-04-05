package com.example.desafio2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio2.R
import com.example.desafio2.models.CartModel

class HistoryAdapter(private var historyList: List<CartModel>) : RecyclerView.Adapter<HistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder { // Pinta los atributos
        val layoutInflater = LayoutInflater.from(parent.context)
        return HistoryViewHolder(layoutInflater.inflate(R.layout.item_cart, parent, false))
    }

    // Recorre cada uno de los items de la lista y llama el render del viewHolder
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val history = historyList[position]
        holder.render(history)
    }

    override fun getItemCount(): Int = historyList.size

    fun setCartList(newCartList: List<CartModel>){
        this.historyList = newCartList
        notifyDataSetChanged()
    }
}