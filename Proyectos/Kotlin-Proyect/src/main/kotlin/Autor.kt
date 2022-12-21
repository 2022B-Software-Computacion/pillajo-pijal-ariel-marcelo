import java.util.Date

class Autor
    (
        nombre: String,
        fechaNacimiento: Date,
        private var numPublicaciones: Int, // Cuando le pongo private deja de ser un parametro y pasa a ser atributo pasa a ser una variable local
        id: Int  = 0,
    ): Persona(
        nombre,
        fechaNacimiento,
    ) {

    // id estático
    companion object {
        var defaultId: Int = 0
    }

    init {
        if (id == 0) {
            defaultId++;
        } else {
            defaultId = id;
        }
    }

        override fun toString(): String {
            return "id=$defaultId, Autor=${this.getNombre()}, fechaNacimiento=${this.getFechaNacimiento()}, numPublicaciones=$numPublicaciones"
        }


    }


