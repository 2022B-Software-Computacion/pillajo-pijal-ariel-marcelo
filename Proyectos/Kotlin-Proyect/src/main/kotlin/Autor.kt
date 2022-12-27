import java.util.*

class Autor(
    nombre: String,
    fechaNacimiento: Date,
    private val numPublicaciones: Int, // Cuando le pongo val es como si creará una variable local para mi autor
    id: String? = null,
): Persona(
        nombre,
        fechaNacimiento,
    ) {
        var uniqueID = UUID.randomUUID().toString().substring(0, 8);
         init {
            if (id != null) this.uniqueID = id;
        }

        @JvmName("getUniqueID1")
        fun getUniqueID(): String {
            return this.uniqueID;
        }

        override fun toString(): String {
            return "idAutor=$uniqueID, Autor=${this.getNombre()}, fechaNacimiento=${this.getFechaNacimiento()}, numPublicaciones=$numPublicaciones"
        }
    }


