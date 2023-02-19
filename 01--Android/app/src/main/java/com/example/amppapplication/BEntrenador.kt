package com.example.amppapplication

import android.os.Parcel
import android.os.Parcelable

/*La clase Parcelable permite serializar y deserializar objetos para poder pasarlos entre actividades
 serializar es convertir un objeto en una secuencia de bytes y deserializar es lo inverso*/
/*Cada actividad es independiente por lo cual no puede compartir objetos a menos que se serializen
 y se compartan a través de un intent o se guarden en un bundle*/
class BEntrenador(
    var id: Int,
    var nombre: String?,
    var descripcion: String?,
) : Parcelable {
    // el :this llama a un constructor principal para asignar los valores del Parcel a las variables globales
    constructor(parcel: Parcel) : this(
        parcel.readInt(), // estoy deserializando el id
        parcel.readString(), // estoy deserializando el nombre
        parcel.readString() // estoy deserializando la descripcion
    ) {}

    override fun toString(): String {
        return "${nombre} - ${descripcion}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)// serializando el id
        parcel.writeString(nombre) // serializando el nombre
        parcel.writeString(descripcion) // serializando la descripcion
    }

    override fun describeContents(): Int {
        return 0
    }
    /*, el companion object permite definir métodos y propiedades estáticas para una clase y
    también puede tener un constructor y tener estado, lo que permite crear instancias
    de la clase asociada.*/
    /*Parcelabe.Creator es necesaria para deserializar objetos de la clase BEntrenador que se
    almacenan en un Bundle o un Intent.*/

    companion object CREATOR : Parcelable.Creator<BEntrenador> {
        override fun createFromParcel(parcel: Parcel): BEntrenador {
            return BEntrenador(parcel)
        }

        override fun newArray(size: Int): Array<BEntrenador?> {
            return arrayOfNulls(size)
        }
    }

}