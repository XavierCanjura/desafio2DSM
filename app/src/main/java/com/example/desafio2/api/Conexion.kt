package com.example.desafio2.api

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query

object Conexion {
    private var database: FirebaseDatabase = FirebaseDatabase.getInstance()

    fun getData(reference: String) : Query
    {
        return this.database.getReference(reference)
    }

}