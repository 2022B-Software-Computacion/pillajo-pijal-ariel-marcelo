import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date;
open class Persona (
    private val nombre: String,
    private val fechaNacimiento: Date,
    ) {

    fun getNombre(): String {
        return this.nombre;
    }

    fun getFechaNacimiento(): Date {
        return this.fechaNacimiento;
    }

    override fun toString(): String {
        return "nombre='$nombre', fechaNacimiento=$fechaNacimiento"
    }

}
