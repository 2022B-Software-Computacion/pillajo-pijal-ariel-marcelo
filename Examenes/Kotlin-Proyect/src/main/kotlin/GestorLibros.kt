class GestorLibros (){
    private var librosAutores: HashMap<Autor, ArrayList<Libro> >;

    init {
        librosAutores = HashMap<Autor, ArrayList<Libro>>();
    }
    // Create
    fun agregarLibro(libro: Libro, idAutor: String){
        // SI el autor NO existe
        val autor: Autor? = librosAutores.keys.find { it.getUniqueID() == idAutor }
        if (autor != null) {
            println("Este autor SI existe")
            // Si el autor ya tiene el libro no se agrega
            if (librosAutores.get(autor)?.contains(libro)!!){
                println("El autor ya tiene el libro")
                println(autor)
            } else {
                librosAutores.get(autor)?.add(libro);
                println("El libro ha sido agregado")
            }
        }
        println("No existe un autor que asociar con el libro que se intenta crear")
    }
    fun agregarAutor(autor: Autor){
        if(!librosAutores.containsKey(autor)){
            librosAutores.put(autor, arrayListOf());
        } else {
            println("El autor ya existe")
        }
    }
    // Read
    fun listarLibros(autor: Autor): ArrayList<Libro>? {
        return librosAutores.get(autor);
    }
    // Update
    fun actualizarLibro(libro: Libro){
        // Si el libro existe
        librosAutores.forEach { (autor, libros) ->
            val libroActual = libros.find { it.getUniqueID() == libro.getUniqueID() }
            if (libroActual != null){
                // Actualizar el libro
                libros.set(libros.indexOf(libroActual), libro);
            }
        }
    }
    fun actualizarAutor(autor: Autor){
        // Si el autor existe
        val autorActual: Autor? = librosAutores.keys.find { it.getUniqueID() == autor.getUniqueID() }
        if (autorActual != null){
            // Actualizar el autor
            val libros: java.util.ArrayList<Libro>? = librosAutores.get(autorActual);
            librosAutores.remove(autorActual);
            librosAutores.put(autor, libros!!);
        } else {
            println("No existe un autor con el id ${autor.getUniqueID()}")
        }
    }
    // Delete
    fun eliminarLibro(idLibro: String){
        // Eliminar el libro
        librosAutores.forEach { (autor, libros) ->
            val libroActual = libros.find { it.getUniqueID() == idLibro }
            if (libroActual != null){
                libros.remove(libroActual);
            }
        }

    }
    fun eliminarAutor(idAutor: String){
        // Eliminar el autor
        val autor: Autor? = librosAutores.keys.find { it.getUniqueID() == idAutor }
        if (autor != null && librosAutores.get(autor)?.size == 0){
            librosAutores.remove(autor);
        }
    }
    // Setters
    fun setLibrosAutores(librosAutores: HashMap<Autor, ArrayList<Libro>>) {
        this.librosAutores = librosAutores;
    }

    // Getters
    fun getAutor(idAutor: String): Autor? {
        return librosAutores.keys.find { it.uniqueID == idAutor };
    }
    fun getLibro(idLibro: String): Libro? {
        librosAutores.values.forEach {
            val libro = it.find { it.uniqueID == idLibro };
            if (libro != null) {
                return libro;
            }
        }
        return null;
    }
    // String
    override fun toString(): String {
        var salida: String = "";
        librosAutores.forEach { libroAutor ->
            salida += "${libroAutor.key.toString()}\n";
            libroAutor.value.forEach { libro ->
                salida += "\t ${libro.toString()}\n";
            }
        }
        return salida;
    }

}