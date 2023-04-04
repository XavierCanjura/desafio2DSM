package com.example.desafio2.models

class ProductModel {
    var id: String? = ""
    var indicaciones: String? = ""
    var contraIndicaciones: String? = ""
    var imagen: String? = ""
    var nombre: String? = ""
    var precio: String? = ""

    constructor(){}

    constructor(id: String?, indicaciones: String?, contraIndicaciones: String?, imagen: String?, nombre: String?, precio: String?){
        this.id = id
        this.indicaciones = indicaciones
        this.contraIndicaciones = contraIndicaciones
        this.imagen = imagen
        this.nombre = nombre
        this.precio = precio
    }
}