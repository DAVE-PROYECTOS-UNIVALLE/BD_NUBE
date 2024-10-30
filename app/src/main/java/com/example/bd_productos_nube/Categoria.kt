package com.example.bd_productos_nube

data class Categoria(
    var id : String="",// por defecto
    var nombre: String = "",
    var descripcion : String? =null //atributos nulos/vacios
)
