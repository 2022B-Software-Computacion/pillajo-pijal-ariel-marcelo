class GestorLibros (){
    private var librosAutores: HashMap<Autor, ArrayList<Libro> >;

    init {
        librosAutores = HashMap<Autor, ArrayList<Libro>>();
    }
    // Create
    fun agregarLibro(libro: Libro, autor: Autor){
        // SI el autor NO existe
        if(!librosAutores.containsKey(autor)){
            var arr: ArrayList<Libro> = arrayListOf();
            arr.add(libro);
            librosAutores.put(autor, arr);
        } else {
            librosAutores.get(autor)?.add(libro);
        }
    }

    fun agregarLibro(autor: Autor){
        // SI el autor NO existe
        if(!librosAutores.containsKey(autor)){
            var arr: ArrayList<Libro> = arrayListOf();
            librosAutores.put(autor, arrayListOf());
        }
    }
    // Read
    fun listarLibros(autor: Autor): ArrayList<Libro>? {
        return librosAutores.get(autor);
    }

    // Update
    fun actualizarLibro(index:Int, libro: Libro, autor: Autor){
        // SI el autor existe
        if(librosAutores.containsKey(autor)){
            librosAutores.get(autor)?.set(index, libro);
        }
    }

    // Delete
    fun eliminarLibro(index: Int, autor: Autor){
        // SI el autor existe
        if(librosAutores.containsKey(autor)){
            librosAutores.get(autor)?.removeAt(index);
        }
    }

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

    fun setLibrosAutores(librosAutores: HashMap<Autor, ArrayList<Libro>>) {
        this.librosAutores = librosAutores;
    }
}