package com.example.app_proyecto2.view.Lista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.example.app_proyecto2.R
import com.example.app_proyecto2.autenticacion.UsuarioAutorizado
import com.example.app_proyecto2.entities.Lista
import com.example.app_proyecto2.entities.Usuario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ConfigurarLista : AppCompatActivity() {

    // Referencias Firestore
    val db = Firebase.firestore
    val coleccionLista = db.collection("Lista")
    val coleccionUsuario = db.collection("Usuario")

    // Lista de usuarios (miembros)
    val listaUsuarios: MutableList<Usuario> = ArrayList()
    val datosListView: MutableList<String> = ArrayList()
    var itemIndex = -1

    // Intent
    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 401

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configurar_lista)

        // Recibir intent
        val lista = intent.getParcelableExtra<Lista>("lista")

        // Campos
        val txtTitulo = findViewById<EditText>(R.id.et_tituloConfigurarLista)
        val listViewMiembros = findViewById<ListView>(R.id.lv_miembrosLista)

        // Rellenar campo de titulo
        txtTitulo.setText(lista?.nombre)

        // Rellenar lista de miembros
        //obtenerUsuarios(lista)
        listViewMiembros.setOnItemClickListener { adapterView, view, position, l ->
            if (position == datosListView.size - 1) {
                mostrarDialogoNuevoMiembro(lista)
            }
        }
        registerForContextMenu(listViewMiembros)

        // Boton actualizar cambios
        val botonActualizar = findViewById<Button>(R.id.btn_actualizarCambiosLista)
        botonActualizar.setOnClickListener {
            if (txtTitulo.text.isEmpty()) {
                val msg = Toast.makeText(this, "Ingrese un título válido", Toast.LENGTH_SHORT)
                msg.show()
            } else {
                actualizarCambios(lista)
            }
        }

        // Boton eliminar lista
        val botonEliminarLista = findViewById<ImageButton>(R.id.btn_eliminarLista)
        botonEliminarLista.setOnClickListener {
            eliminarLista(lista)
        }

        // Solo el propietario puede cambiar datos  de la lista
        if (lista!!.correoPropietario != UsuarioAutorizado.email) {
            txtTitulo.isEnabled = false
            botonEliminarLista.visibility = Button.INVISIBLE
            botonActualizar.visibility = Button.INVISIBLE
        }

    }

    // Listas
    fun obtenerUsuarios(lista: Lista?) {
        coleccionUsuario
            .whereIn("correo", lista?.usuarios?.map { it.correo }!!.toMutableList())
            .get()
            .addOnSuccessListener { documents ->
                documents.forEach { doc ->
                    listaUsuarios.add(Usuario(
                        doc["uid"].toString(),
                        doc["nombre"].toString(),
                        doc["apellido"].toString(),
                        doc["correo"].toString()
                    ))
                }
                actualizarListViewMiembros()
            }
    }

    // Actualizar lista
    fun actualizarCambios(lista: Lista?) {
        val txtTitulo = findViewById<EditText>(R.id.et_tituloConfigurarLista)
        db.runTransaction{ transaction ->
            transaction.update(
                coleccionLista.document(lista?.id.toString()),
                mapOf(
                    "nombre" to txtTitulo.text.toString(),
                    "usuarios" to listaUsuarios.map{it.correo}.toMutableList()
                )
            )
        }.addOnSuccessListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Lista actualizada con éxito")
            builder.setPositiveButton("Aceptar") { _, _ ->
                val nuevaLista = Lista(
                    lista!!.id,
                    txtTitulo.text.toString(),
                    ArrayList(listaUsuarios),
                    lista.correoPropietario,
                    lista.etiquetas
                )
                abrirActividadEnviandoLista(ListaDeActividades::class.java, nuevaLista)
            }
            val dialogo = builder.create()
            dialogo.setCancelable(false)
            dialogo.show()
        }
    }

    // Eliminar lista
    fun eliminarLista(lista: Lista?) {
        // Solo el propietario de la lista puede eliminarla
        if (lista!!.correoPropietario == UsuarioAutorizado.email) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Eliminar lista")
            builder.setMessage("¿Está seguro de que desea eliminar la lista ${lista.nombre}?")
            builder
                .setPositiveButton("Eliminar", null)
                .setNegativeButton("Cancelar", null)
            val dialog = builder.create()
            dialog.setCancelable(false)
            dialog.show()

            // Eliminar
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setOnClickListener {
                    // Elimina cada actividad dentro de la lista
                    val coleccionActividades = db.collection("Lista/${lista.id}/Actividad")
                    coleccionActividades
                        .get()
                        .addOnSuccessListener { documents ->
                            documents.forEach { doc ->
                                coleccionActividades.document(doc.id)
                                    .delete()
                            }
                            // Elimina la lista
                            coleccionLista
                                .document(lista.id!!)
                                .delete()
                                .addOnSuccessListener {
                                    // Se deshabilita el boton
                                    val botonActualizar = findViewById<Button>(R.id.btn_actualizarCambiosLista)
                                    botonActualizar.isEnabled = false
                                    // Retroalimentacion
                                    val msg = Toast.makeText(this, "Lista eliminada exitosamente", Toast.LENGTH_SHORT)
                                    msg.show()
                                    // Retorna a la lista de actividades sin indicar una lista especifica
                                    val intent = Intent(this, ListaDeActividades::class.java)
                                    startActivity(intent)
                                }
                        }
                }
        }
        // Caso contrario (usuario no propietario)
        else {
            val msg = Toast.makeText(
                this,
                "Solo el propietario de la lista puede eliminarla",
                Toast.LENGTH_SHORT
            )
            msg.show()
        }
    }

    fun mostrarDialogoNuevoMiembro(lista: Lista?) {
        val builder = AlertDialog.Builder(this)
        val txtCorreo = EditText(this)
        txtCorreo.hint = "ejemplo@mail.com"
        txtCorreo.background = resources.getDrawable(R.drawable.et_borders)
        val scale = resources.displayMetrics.density
        txtCorreo.width = (10 * scale + 0.5f).toInt()
        txtCorreo.height = (48 * scale + 0.5f).toInt()
        txtCorreo.setPadding(100, txtCorreo.paddingTop, 100, txtCorreo.paddingBottom)
        txtCorreo.setEms(10)

        builder.setTitle("Añadir miembro")
        builder.setMessage("Correo electrónico:")
        builder.setView(txtCorreo)
        builder
            .setPositiveButton("Aceptar", null)
            .setNegativeButton("Cancelar", null)

        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            .setOnClickListener {
                // Campo vacio
                if (txtCorreo.text.isEmpty()) {
                    val msg = Toast.makeText(this, "Llene el campo de correo", Toast.LENGTH_SHORT)
                    msg.show()
                }
                // Usuario que ya es miembro de la lista
                else if ( lista?.usuarios?.map{it.correo}!!.contains(txtCorreo.text.toString()) ) {
                    val usuarioExistenteMsg = Toast.makeText(
                        this,
                        "Ese usuario ya es miembro de esta lista",
                        Toast.LENGTH_SHORT
                    )
                    usuarioExistenteMsg.show()
                }
                // Se registra el usuario si este existe en la BD
                else {
                    registrarSiEsValido(txtCorreo.text.toString())
                    dialog.dismiss()    // Cuando ya se registre
                }
            }
    }

    fun actualizarListViewMiembros() {
        datosListView.clear()
        listaUsuarios.forEach {
            datosListView.add(it.toString())
        }
        datosListView.add(Usuario.AGREGAR_MIEMBRO)  // Opcion para agregar miembros

        val listViewMiembros = findViewById<ListView>(R.id.lv_miembrosLista)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            datosListView
        )
        listViewMiembros.adapter = adapter
    }

    fun registrarSiEsValido(correo: String) {
        coleccionUsuario
            .whereEqualTo("correo", correo)
            .get()
            .addOnSuccessListener { documents ->
                if ( documents.size() == 1 ) {
                    val usuario = documents.documents[0]
                    listaUsuarios.add(Usuario(
                        usuario["uid"].toString(),
                        usuario["nombre"].toString(),
                        usuario["apellido"].toString(),
                        usuario["correo"].toString()
                    ))
                    actualizarListViewMiembros()
                } else {
                    val usuarioNoExistenteMsg = Toast.makeText(
                        this,
                        "Ese usuario no existe",
                        Toast.LENGTH_SHORT
                    )
                    usuarioNoExistenteMsg.show()
                }
            }
    }

    // Eliminar miembro
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_eliminar, menu)

        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        itemIndex = id

        // Ocultar y mostrar opciones correspondientes
        val opcionEliminar: MenuItem = menu!!.findItem(R.id.opcion_eliminar)

        // Solo el propietario puede eliminar miembros
        val lista = intent.getParcelableExtra<Lista>("lista")
        if (UsuarioAutorizado.email == lista!!.correoPropietario) {
            // La opcion se deshabilita para la opcion "+ Agregar miembro"
            opcionEliminar.isVisible = itemIndex != datosListView.size - 1
        } else {
            opcionEliminar.isVisible = false
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        // Se adquiere el correo a eliminar
        val correoAEliminar = listaUsuarios[itemIndex].correo
        return when (item?.itemId) {
            R.id.opcion_eliminar -> {
                // El creador de la lista no puede eliminarse a si mismo
                if (correoAEliminar == UsuarioAutorizado.email) {
                    val msg = Toast.makeText(
                        this,
                        "No se puede eliminar a usted mismo",
                        Toast.LENGTH_SHORT
                    )
                    msg.show()
                }
                // Eliminar a otro miembro
                else {
                    listaUsuarios.removeAt(itemIndex)
                    actualizarListViewMiembros()
                }
                return true
            }
            else -> return super.onContextItemSelected(item)
        }
    }

    fun abrirActividadEnviandoLista(clase: Class<*>, lista: Lista) {
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("listaSeleccionada", lista)
        startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)
    }

    override fun onBackPressed() {
        // Recibir intent
        val lista = intent.getParcelableExtra<Lista>("lista")
        abrirActividadEnviandoLista(ListaDeActividades::class.java, lista!!)
    }

}