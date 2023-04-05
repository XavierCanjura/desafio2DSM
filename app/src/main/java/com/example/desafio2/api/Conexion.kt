package com.example.desafio2.api

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.desafio2.models.CartModel
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import java.util.Date

object Conexion {
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()

    fun getData(reference: String) : Query
    {
        return this.database.getReference(reference)
    }

    fun getDataByParam(reference: String): DatabaseReference {
        return this.database.getReference(reference)
    }


    fun addCart(reference: String, instant: Date, cart: CartModel): Task<Void> {
        return this.database.getReference(reference).child(instant.toString()).setValue(cart)
    }

    fun changeStateCart(reference: String, id: String): Task<Void> {
        val update = mapOf<String, Any>("estado" to "comprado")
        return this.database.getReference(reference).child(id).updateChildren(update)
    }

    fun removeCart(reference: String, id: String): Task<Void> {
        return this.database.getReference(reference).child(id).removeValue()
    }

}