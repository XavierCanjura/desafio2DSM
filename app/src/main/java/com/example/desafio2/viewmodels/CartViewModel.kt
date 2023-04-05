package com.example.desafio2.viewmodels

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio2.api.Conexion
import com.example.desafio2.models.CartModel
import com.example.desafio2.models.ProductModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class CartViewModel: ViewModel() {
    private var cartLiveData = MutableLiveData<List<CartModel>>()

    fun getCartByUser(user: String){
        Conexion.getData("compras" ).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    val carts = snapshot.children.mapNotNull {
                        it.getValue(CartModel::class.java)
                    }.toList()

                    var cartByUser: MutableList<CartModel> = ArrayList()

                    for (cart in carts){
                        if (cart.cliente == user && cart.estado == "carrito") {
                            cartByUser.add(cart)
                        }
                    }

                    cartLiveData.value = cartByUser
                }
            }
            override fun onCancelled(error: DatabaseError) { }
        })
    }

    fun updateCart(cart: CartModel) {
        val id = cart.id.toString()
        Conexion.changeStateCart("compras", id)

    }

    fun deleteCart(id: String){
        Conexion.removeCart("compras", id);
    }


    fun observerCartLiveData(): LiveData<List<CartModel>> {
        return cartLiveData
    }
}