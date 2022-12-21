import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date;

class Libro
    (
    private var autor: Autor,
    private var titulo: String,
    private var fechaPublicacion: Date,
    private var bestSeller: Boolean,
    private var valor: Double,
    private var numeroDeCopias: Int,
    ){
        //val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        // Crear un id para cada libro
        companion object {
            var id: Int = 0
        }

        init {
            id++;
        }

        override fun toString(): String {
            return "idLibro='$id', titulo='$titulo', fechaPublicacion=$fechaPublicacion, bestSeller=$bestSeller, valor=$valor, numeroDeCopias=$numeroDeCopias"
        }


}
