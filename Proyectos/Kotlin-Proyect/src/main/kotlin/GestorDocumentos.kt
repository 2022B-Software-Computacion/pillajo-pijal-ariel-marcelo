import java.io.File
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class GestorDocumentos (
    private val path: String,
        ){

    fun writeFile(content: String) {
      //"$path\\src\\main\\resources\\Autor.txt"
      File(this.path).printWriter().use {
              out -> out.println(content)
      }
  }
    fun readFile(): HashMap<Autor, ArrayList<Libro>> {
        val inputStream: InputStream = File(this.path).inputStream();
        val librosAutores: HashMap<Autor, ArrayList<Libro> > = HashMap<Autor, ArrayList<Libro>>();
        val autores: ArrayList<Autor> = arrayListOf();
        inputStream.bufferedReader().useLines { lines -> lines.forEach {
            val arr = it.split(",")
            // Si es un autor
            if (arr[0].split("=")[0] == "idAutor") {
                val autor = Autor(
                    arr[1].split("=")[1],
                    Utils.stringToDate(arr[2].split("=")[1]),
                    arr[3].split("=")[1].toInt(),
                    arr[0].split("=")[1],
                )
                autores.add(autor);
                librosAutores.put(autor, arrayListOf())
            } else if (arr[0].split("=")[0] == "\t idLibro") {
                    val miAutor = autores.last();
                    librosAutores.get(miAutor)?.add(
                        Libro(
                            miAutor, arr[1].split("=")[1],
                            Utils.stringToDate(arr[2].split("=")[1]),
                            arr[3].split("=")[1].toBoolean(),
                            arr[4].split("=")[1].toDouble(),
                            arr[5].split("=")[1].toInt(),
                            arr[0].split("=")[1],
                        )
                    )
            }
        }
        }
        return librosAutores;
    }
}