import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date;
open class Persona (
    private val nombre: String,
    private val fechaNacimiento: Date,
    // opcional
    private val id: Int = 0,
    ) {

    // id estático
    companion object {
        var defaultId: Int = 0
    }

    init {
        this.fechaNacimiento;
        this.nombre;
        if (id == 0) {
            defaultId++;
        } else {
            defaultId = id;
        }
    }

    fun getNombre(): String {
        return this.nombre;
    }

    fun getFechaNacimiento(): Date {
        return this.fechaNacimiento;
    }

    override fun toString(): String {
        return "id=$defaultId, nombre='$nombre', fechaNacimiento=$fechaNacimiento"
    }

}
