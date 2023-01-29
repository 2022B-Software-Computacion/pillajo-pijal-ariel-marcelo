package com.example.amppapplication

class BBaseDatosMemoria {
    companion object {
        val arregloBEntrenador = ArrayList<BEntrenador>()
        init {
            arregloBEntrenador
                .add(
                    BEntrenador(1,"Ariel", "a@acom")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(2,"Luis", "b@bcom")
                )
            arregloBEntrenador
                .add(
                    BEntrenador(3, "Carlos", "c@ccom")
                )
        }
    }
}