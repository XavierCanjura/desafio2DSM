package com.example.desafio2.viewmodels

import android.os.Build
import android.util.Log
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
import java.util.*

class ProductViewModel: ViewModel() {
    private var productLiveData = MutableLiveData<List<ProductModel>>()

    fun getProducts()
    {
        Conexion.getData("products").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists())
                {
                    val products = snapshot.children.mapNotNull {
                        it.getValue(ProductModel::class.java)
                    }.toList()
                    productLiveData.value = products

                } else {
                    Log.d("ERROR", "OCURRIO UN PROBLEMA")
                    productLiveData.postValue(emptyList())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("ERROR", error.message.toString())
            }
        })
    }


    fun addCart(producto: String?, cliente: String?, imagen: String?, precio: String?) {
        val instant = Date()
        val cart = CartModel(instant.toString(), producto, cliente, imagen, precio, "carrito")

        Conexion.addCart("compras", instant, cart).addOnSuccessListener {
            true
        }.addOnFailureListener {
            false
        }
    }

    fun observerProductLiveData(): LiveData<List<ProductModel>> {
        return productLiveData
    }
}