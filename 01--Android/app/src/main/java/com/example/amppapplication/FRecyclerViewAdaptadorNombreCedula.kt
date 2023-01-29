package com.example.amppapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
/* RecyclerView es una clase importante en Android que permite mostrar elementos en una lista
de forma eficiente y personalizable, incluye funcionalidades interesantes como actualizar la
lista a medida que el usuario hace scrooll, para usarla se necesita de un adaptador capaz de
proporcionar la info para mostrar*/
/**/
/*RecyclerView.Adapter es responsable de crear las vistas necesarias para mostrar cada
    elemento de la lista, y también se encarga de reciclar las vistas que ya no se están
    utilizando para reducir el uso de memoria. Los métodos principales que se suelen
    sobreescribir en un adaptador de RecyclerView son onCreateViewHolder, onBindViewHolder
    y getItemCount, los cuales se utilizan para crear las vistas, vincularlos con los
    datos y obtener el número de elementos respectivamente.*/
class FRecyclerViewAdaptadorNombreCedula(
    private val contexto: GRecyclerView, // Contexto
    private val lista: ArrayList<BEntrenador>, // datos a ingresar en la lista
    private val recyclerView: RecyclerView // lista en la vista para meter los datos
): RecyclerView.Adapter<FRecyclerViewAdaptadorNombreCedula.MyViewHolder>() {
    /*Al extender RecyclerView.Adapter, se debe especificar el tipo de objeto ViewHolder
    que se utilizará con el adaptador. En este caso, se especifica que se utilizará la clase
    interna MyViewHolder.*/
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        /*ViewHolder es un patrón de diseño utilizado en RecyclerView para mejorar el
        rendimiento y la eficiencia al tratar con grandes cantidades de datos.
        El objetivo principal de ViewHolder es mantener una referencia a las vistas
        que se utilizan para representar un elemento de la lista, de manera que no tenga
        que buscarlas cada vez que se necesiten.*/

        //Varias visas representan un elemento de la lista
        // Cada elemento de la lista se representa por un ViewHolder
        val nombreTextView: TextView
        val cedulaTextView: TextView
        val likesTextView: TextView
        val accionButton: Button
        var numeroLikes = 0
        init {
            // Se guardan las referencias a las vistas destinadas a representar a un elemento
            // Solo mantiene en memoria las vistas que se estan mostrando lo cual es eficiente
            nombreTextView = view.findViewById(R.id.tv_nombre)
            cedulaTextView = view.findViewById(R.id.tv_cedula)
            likesTextView = view.findViewById(R.id.tv_likes)
            accionButton = view.findViewById<Button>(R.id.btn_dar_like)
            accionButton.setOnClickListener { anadirLike() }
        }
        fun anadirLike(){
            numeroLikes =  numeroLikes + 1
            likesTextView.text = numeroLikes.toString()
            contexto.aumentarTotalLikes()// función que existe en el gRecyclerView
            // recyclerView.adapter?.notifyDataSetChanged()
        }
    }
    // Este método es responsable de crear una nueva instancia de viewHolder
    // Asociar la vista que se utilizará para representar un elemento de la lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_vista,// Archivo de diseño para representar un elemento de la vista
                parent, // objeto ViewGroup que almacena objetos view y aqui solo le digo que la vista inflada se agregará a que vista padre
                false// La visa inflada se agrega a la vista padre?
            )
        // Crea una nueva instancia de MyViewHolder ya que el .inflate retorna un view
        return MyViewHolder(itemView)
    }

    /*onBindViewHolder() se utiliza para vincular un elemento de datos específico de la lista
    con una vista específica en el ViewHolder.*/
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Saco un entrenador de la lista segun su posicion
        val entrenadorActual = this.lista[position]
        // Actualizo la vista en la que quiero mostrar el elemento entrenador
        // Recuerda que tienes la clase MyViewHolder como clase interna
        holder.nombreTextView.text = entrenadorActual.nombre
        holder.cedulaTextView.text = entrenadorActual.descripcion
        holder.accionButton.text = "Like ${entrenadorActual.nombre}"
        holder.likesTextView.text = "0"
    }
    // tamano del arreglo
    override fun getItemCount(): Int {
        return this.lista.size
    }
}