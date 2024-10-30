package com.example.bd_productos_nube

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    private lateinit var categoriaControlador: Cat_Controlador
    private lateinit var editNombre:EditText
    private lateinit var editDesc: EditText
    private lateinit var btnAgregar : Button
    private lateinit var listVCategorias: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val categoriasList= mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editDesc= findViewById(R.id.editDesc)
        editNombre= findViewById(R.id.editNombre)
        btnAgregar= findViewById(R.id.btnAgregar)
        listVCategorias = findViewById(R.id.listCategorias)

        categoriaControlador=Cat_Controlador()

        adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            categoriasList)
        listVCategorias.adapter= adapter

        btnAgregar.setOnClickListener {
            agregarCat()
        }
        cargarCat()


/*
        categoriaControlador=Cat_Controlador()

        val nuevaCategoria = Categoria(nombre = "Frutas",
            descripcion = "sector de frutas tropicales")
        categoriaControlador.agregarCategoria(nuevaCategoria)*/

    }
    private fun agregarCat(){
        val nombre = editNombre.text.toString()
        val descrip = editDesc.text.toString()

        if(nombre.isNotEmpty()){
            val nuevaCategoria = Categoria(nombre = nombre,
                descripcion = descrip)
            categoriaControlador.agregarCategoria(nuevaCategoria)

            editNombre.text.clear()
            editDesc.text.clear()

            cargarCat()
        }
        else{
            editNombre.error= "el nombre esta vacio"
        }
    }
    private fun cargarCat(){
        categoriaControlador.obtenerCategorias {
            categorias ->
            categoriasList.clear()

            for(categoria in categorias){
                categoriasList.add(categoria.nombre+
                " "+ categoria.descripcion)
            }
            adapter.notifyDataSetChanged()
        }
    }
}