package com.example.amppapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {


    /*La variable "contenidoIntentExplicito" es una variable que
    almacena el resultado de la función "registerForActivityResult()".
    La función "registerForActivityResult()" recibe como primer parámetro
    un objeto "ActivityResultContracts.StartActivityForResult()", el cual
    es una clase que se encarga de iniciar una actividad para recibir un resultado.
    El segundo parámetro es una función lambda (un bloque de código anónimo) que
    se ejecuta cuando la actividad secundaria finaliza y devuelve un resultado.
    Dentro de esta función lambda, se comprueba si el resultado es "Activity.RESULT_OK"
    lo que significa que la actividad secundaria finalizó correctamente.
    Se comprueba si el resultado tiene un objeto data, si es así se recoge el valor de la variable
    "nombreModificado" y se imprime en el log con el tag "intent-epn".*/
    val contenidoIntentExplicito =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                if (result.data != null) {
                    val data = result.data
                    Log.i("intent-epn", "${data?.getStringExtra("nombreModificado")}")
                }
            }
        }

    /*El objeto "contentResolver" es una clase que proporciona una interfaz para interactuar
     con el almacenamiento de datos de una aplicación, incluyendo bases de datos, archivos
     y preferencias. En este caso, se está utilizando el objeto "contentResolver" para realizar
     una consulta a la base de datos de contactos. La función "query()" se utiliza para realizar
     una consulta a una base de datos. Esta función recibe varios parámetros:
     La primera es el URI (identificador uniforme de recursos) de la tabla de la base de datos a la que se
     desea hacer la consulta. En este caso, se está utilizando el objeto "uri" que se obtuvo del
     resultado de la actividad secundaria.
     El segundo parámetro es un arreglo de las columnas que se desean recuperar en la consulta.
     En este caso se esta pasando un "null" para recuperar todas las columnas.
    El tercer y cuarto parámetros es una clausula "WHERE" y una clausula "WHERE" de argumentos
    respectivamente, se esta pasando "null" ya que no es necesario en este caso.
    El quinto y sexto parámetros son para ordenar y limitar los resultados respectivamente,
    se esta pasando "null" ya que no es necesario en este caso.*/
    val contenidoIntentImplicito = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
            result ->
        if(result.resultCode == RESULT_OK){
            if(result.data != null){
                if (result.data!!.data != null){
                    val uri: Uri = result.data!!.data!!
                    val cursor = contentResolver.query(
                        uri,// Esta uri va a ser el path al contacto seleccionado
                        null,
                        null,
                        null,
                        null,
                        null
                    )
                    /*La función "moveToFirst()" se utiliza para mover el cursor al primer registro
                    de los resultados de la consulta. Es importante llamar a esta función antes de
                    intentar acceder a los datos de los registros, ya que si no se hace, el cursor
                    se encontrará en una posición "fuera de los registros" y no se podrá acceder
                    a ningún dato.*/
                    cursor?.moveToFirst()
                    val indiceTelefono = cursor?.getColumnIndex(
                        /*La clase "ContactsContract.CommonDataKinds.Phone" es una clase de Android
                        que define constantes para trabajar con los datos de los números de
                        teléfono de los contactos. La constante "NUMBER" es una de estas constantes
                        y se utiliza para identificar la columna que contiene el número de teléfono
                        de un contacto en la base de datos de contactos.*/
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                    val telefono = cursor?.getString(
                        indiceTelefono!!
                    )
                    // es importante cerrar el cursor para liberar los recursos
                    cursor?.close()
                    Log.i("intent-epn", "Telefono ${telefono}")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Base de datos sqlite
        EBaseDeDatos.tablaEntrenador = ESqliteHelperEntrenador(this)

        val botonCicloVida = findViewById<Button>(R.id.btn_ciclo_vida)
        botonCicloVida
            .setOnClickListener {
                irActividad(ACicloVida::class.java)
            }

        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView
            .setOnClickListener {
                irActividad(BListView::class.java)
            }

        val botonIntentImplicito = findViewById<Button>(R.id.btn_ir_intent_implicito)
        botonIntentImplicito
            .setOnClickListener {
                val intentConRespuesta = Intent(// Un intent informa de la acción a realizar y los datos necesarios
                    Intent.ACTION_PICK,// Se define la acción a realizar con el intent
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI// Selecciona un contacto de la libreta
                )
                // Lanza la actividad secundaria asociada al intent dado
                contenidoIntentImplicito.launch(intentConRespuesta)
            }

        val botonIntent = findViewById<Button>(R.id.btn_intent)
        botonIntent
            .setOnClickListener {
                abrirActividadConParametros(CIntentExplicitoParametros::class.java)
            }

        val botonSqlite = findViewById<Button>(R.id.btn_sqlite)
        botonSqlite
            .setOnClickListener {
                irActividad(ECrudEntrenador::class.java)
            }

        val botonRView = findViewById<Button>(R.id.btn_revcycler_view)
        botonRView
            .setOnClickListener {
                irActividad(GRecyclerView::class.java)
            }

    }
    /*Este código es una función que se utiliza para abrir una actividad específica y enviar
    parámetros a esa actividad. La función recibe un parámetro "clase" que es la clase de la
    actividad a la que se desea navegar.
    La función crea un Intent llamado "intentExplicito" con el objetivo de iniciar la actividad
    especificada en "clase". A continuación, utiliza el método "putExtra" para agregar varios
    parámetros al Intent. y con launch llamaría la actividad específica de esa clase */
    fun abrirActividadConParametros(
        clase: Class<*>,
    ) {
        val intentExplicito = Intent(this, clase)
        // Enviar parametros (solamente variables primitivas)
        intentExplicito.putExtra("nombre", "Adrian")
        intentExplicito.putExtra("apellido", "Eguez")
        intentExplicito.putExtra("edad", 33)
        intentExplicito.putExtra(
            "entrenadorPrincipal",
            BEntrenador(1,"Adrian", "Paleta")
        )

        contenidoIntentExplicito.launch(intentExplicito)
    }


    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}