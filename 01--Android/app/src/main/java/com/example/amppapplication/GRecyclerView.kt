package com.example.amppapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GRecyclerView : AppCompatActivity() {
    var totalLikes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grecycler_view)
        // Definir lista
        val listaEntrenador = arrayListOf<BEntrenador>()
        listaEntrenador
            .add(BEntrenador(1,"Adrian","Eguez"))
        listaEntrenador
            .add(BEntrenador(2,"Vicente","Sarzosa"))
        // Inicializar Recycler View, rv_entrenadores es solo una list View
        val recyclerView = findViewById<RecyclerView>(R.id.rv_entrenadores)
        inicializarRecyclerView(listaEntrenador,recyclerView)
    }

    /* RecyclerView es una clase importante en Android que permite mostrar elementos en una lista
    de forma eficiente y personalizable, incluye funcionalidades interesantes como actualizar la
    lista a medida que el usuario hace scrooll, para usarla se necesita de un adaptador capaz de
    proporcionar la info para mostrar*/
    fun inicializarRecyclerView(
        lista:ArrayList<BEntrenador>,// Contenido a setear en la vista
        recyclerView: RecyclerView // vista
    ){
        // Crear el adaptador
        val adaptador = FRecyclerViewAdaptadorNombreCedula(
            this, // Contexto
            lista, // Arreglo datos
            recyclerView // Recycler view, lista en la vista
        )
        // Setear el adaptador
        recyclerView.adapter = adaptador // Recordemos que el adaptador es el que se encarga de llenar la lista
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator() // Animacion
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()// Notificar cambios
    }


    fun aumentarTotalLikes(){
        totalLikes = totalLikes + 1
        val totalLikesTextView = findViewById<TextView>(R.id.tv_total_likes)
        totalLikesTextView.text = totalLikes.toString()
    }
}