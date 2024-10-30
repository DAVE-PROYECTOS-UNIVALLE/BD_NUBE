package com.example.bd_productos_nube

import javax.security.auth.callback.Callback

interface ICategoria {
    // funciones crud
    fun agregarCategoria(categoria: Categoria)
    fun obtenerCategorias(callback: (List<Categoria>)->Unit)
    fun obtenerCategoriasId(id: String,
                            callback: (Categoria?) -> Unit)
    fun actualizarCategoria(categoria: Categoria)
    fun eliminarCategoria(id: String)
}