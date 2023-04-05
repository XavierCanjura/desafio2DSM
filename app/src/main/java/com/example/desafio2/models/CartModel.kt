package com.example.desafio2.models

class CartModel {
    var id: String? = ""
    var producto: String? = ""
    var cliente: String? = ""
    var imagen: String? = ""
    var precio: String? = ""
    var estado: String? = ""

    constructor() { }

    constructor(id: String?, producto: String?, cliente: String?, imagen: String?, precio: String?, estado: String?){
        this.id = id
        this.producto = producto
        this.cliente = cliente
        this.imagen = imagen
        this.precio = precio
        this.estado = estado
    }

    fun toMap(): Map<String, Any?>{
        return mapOf(
            "id" to id,
            "producto" to producto,
            "cliente" to cliente,
            "imagen" to imagen,
            "precio" to precio,
            "estado" to estado,
        )
    }
}