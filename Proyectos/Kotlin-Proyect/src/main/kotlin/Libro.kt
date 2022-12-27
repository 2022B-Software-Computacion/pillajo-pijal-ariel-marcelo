import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class Libro
    (
    private var autor: Autor,
    private var titulo: String,
    private var fechaPublicacion: Date,
    private var bestSeller: Boolean,
    private var valor: Double,
    private var numeroDeCopias: Int,
    id: String? = null,
    ){
        // UUID de 8 caracteres
        var uniqueID = UUID.randomUUID().toString().substring(0, 8);
        init {
            if (id != null) this.uniqueID = id
        }
        // Setters
        fun setAutor(autor: Autor) {
            this.autor = autor;
        }
        // Getters
        fun getAutor(): Autor {
            return this.autor;
        }
        fun getTitulo(): String {
            return this.titulo;
        }
        fun getFechaPublicacion(): Date {
            return this.fechaPublicacion;
        }
        fun getBestSeller(): Boolean {
            return this.bestSeller;
        }
        fun getValor(): Double {
            return this.valor;
        }
        fun getNumeroDeCopias(): Int {
            return this.numeroDeCopias;
        }
        @JvmName("getUniqueID1")
        fun getUniqueID(): String {
            return this.uniqueID;
        }
        override fun toString(): String {
            return "idLibro=$uniqueID, titulo=$titulo, fechaPublicacion=$fechaPublicacion, bestSeller=$bestSeller, valor=$valor, numeroDeCopias=$numeroDeCopias"
        }


}
