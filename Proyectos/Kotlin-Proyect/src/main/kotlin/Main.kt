import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.HashMap


fun main(args: Array<String>){


    val formatter = SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH)
    val dateString = Date().toString()
    // Volver a convertir a Date
    val mydate: Date = formatter.parse(dateString)
    println(mydate)


    val date = Date()
    // imprime formato de date
    val format = SimpleDateFormat()
    println(format.toPattern())

    val autor: Autor = Autor("Jorge Bastidas", Date(), 10, 1);
    var libro: Libro = Libro(
         autor,
        "Las Marianitas",
        Date(),
        true,
        3.5,
        100,
    )

    var libreria: GestorLibros = GestorLibros();
    libreria.agregarLibro(libro, autor);
    libreria.agregarLibro(libro, autor);
    //println(libreria.toString());
    var libro2: Libro = Libro(
        autor,
        "Las Marianitas2",
        Date(),
        true,
        3.5,
        100,
    )
    // Format of LocalDateTime: 2021-05-25T15:00:00
    val autor2: Autor = Autor("Jorge Bs", Date(), 10);
    libreria.agregarLibro(libro2, autor2);

    // Path directory
    val path = System.getProperty("user.dir")
    val gestorDocumentos = GestorDocumentos("$path\\src\\main\\resources\\Autor.txt")
    val output = gestorDocumentos.readFile()
    libreria.setLibrosAutores(output)
    println(libreria.toString())
    gestorDocumentos.writeFile(libreria.toString());

}