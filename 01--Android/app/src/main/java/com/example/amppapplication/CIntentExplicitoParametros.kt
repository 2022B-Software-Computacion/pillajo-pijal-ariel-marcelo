package com.example.amppapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
// App compat activity is a class that provides support for older versions of android
class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)
        // intent que inicia esta actividad, clase pública
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getIntExtra("edad", 0)
        val entrenador = intent.getParcelableExtra<BEntrenador>(
            "entrenadorPrincipal"
        )
        val boton = findViewById<Button>(R.id.btn_devolver_respuesta)
        boton
            .setOnClickListener { devolverRespuesta() }
    }
    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("nombreModificado", "Vicente")
        intentDevolverParametros.putExtra("edadModificado", 33)
        setResult(
            RESULT_OK, // Si la actividad ser realizó correctamente
            intentDevolverParametros // Parametros de respuesta
        )
        //Devuelve los parámetros de respuesta a la actividad que inició esta actividad
        // (CIntentExplicitoParametros)
        finish()
    }
}