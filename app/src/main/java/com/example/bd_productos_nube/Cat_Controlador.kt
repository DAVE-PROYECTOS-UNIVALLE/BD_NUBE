package com.example.bd_productos_nube

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Cat_Controlador: ICategoria {
    //obteniendo la base de datos
    private val database: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("categorias")
    override fun agregarCategoria(categoria: Categoria) {
        // llave autogenerada por firebase
        val id = database.push().key?:return
        categoria.id= id
        database.child(id).setValue(categoria)
    }

    override fun obtenerCategorias(callback: (List<Categoria>) -> Unit) {
        database.get().addOnSuccessListener {
            captura->
            val categorias = mutableListOf<Categoria>()
            for(child in captura.children){
                val categoria = child.getValue(Categoria::class.java)
                categoria?.let {
                    categorias.add(it)
                }
            }
            callback(categorias)
        }
    }

    override fun obtenerCategoriasId(id: String, callback: (Categoria?) -> Unit) {
        database.child(id).get().addOnSuccessListener {
            captura->
            val categoria= captura.getValue(Categoria::class.java)
            callback(categoria)
        }
    }

    override fun actualizarCategoria(categoria: Categoria) {
        database.child(categoria.id).setValue(categoria)
    }

    override fun eliminarCategoria(id: String) {
        database.child(id).removeValue()
    }
}