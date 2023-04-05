package com.example.desafio2.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio2.api.Conexion
import com.example.desafio2.models.CartModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class HistoryViewModel: ViewModel() {
    private var historyLiveData = MutableLiveData<List<CartModel>>()

    fun getHistoryByUser(user: String){
        Conexion.getData("compras" ).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    val carts = snapshot.children.mapNotNull {
                        it.getValue(CartModel::class.java)
                    }.toList()

                    var cartByUser: MutableList<CartModel> = ArrayList()

                    for (cart in carts){
                        if (cart.cliente == user && cart.estado == "comprado") {
                            cartByUser.add(cart)
                        }
                    }

                    historyLiveData.value = cartByUser
                }
            }
            override fun onCancelled(error: DatabaseError) { }
        })
    }

    fun observerHistoryLiveData(): LiveData<List<CartModel>> {
        return historyLiveData
    }
}