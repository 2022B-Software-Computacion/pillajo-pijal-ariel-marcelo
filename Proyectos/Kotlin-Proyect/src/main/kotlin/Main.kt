import java.util.*


fun main(args: Array<String>){
    var opcion = 0;
    do {
        val scanner = Scanner(System.`in`)
        println("Ingrese el número de la acción a realizar ")
        println("1. Crear ")
        println("2. Leer ")
        println("3. Actualizar ")
        println("4. Eliminar ")
        println("5. Salir ")
        opcion = scanner.nextInt()
        if (opcion == 1) {
            do {
                // Crear libro o usuario
                println("Ingrese el número de la acción a realizar ")
                println("1. Crear Libro ")
                println("2. Crear Autor ")
                println("3. Salir ")
                var opcionC = scanner.nextInt()
                if (opcionC == 1) {
                    if (registrarLibro()) {
                        println("Libro registrado correctamente")
                    } else {
                        println("No existe autor a asociar con ese libro")
                    }
                } else if (opcionC == 2) {
                    registrarAutor();
                }
            } while (opcionC != 3)
        } else if (opcion == 2) {
            // Path directory
            val library = getLibrary();
            println(library.toString())
        } else if (opcion == 3 ) {
            // Actualizar
            do {
                // Crear libro o usuario
                println("Ingrese el número de la acción a realizar ")
                println("1. Actualizar Libro ")
                println("2. Aclntualizar Autor ")
                println("3. Salir ")
                var opcionC = scanner.nextInt()
                if (opcionC == 1) {
                    if (actualizarLibro()) {
                        println("Libro actualizado correctamente")
                    } else {
                        println("No existe ese libro a actualizar")
                    }
                } else if (opcionC == 2) {
                    actualizarAutor();
                }
            } while (opcionC != 3)
        } else if (opcion == 4) {
            // Eliminar
            do {
                // Crear libro o usuario
                println("Ingrese el número de la acción a realizar ")
                println("1. Eliminar Libro ")
                println("2. Eliminar Autor ")
                println("3. Salir ")
                var opcionC = scanner.nextInt()
                if (opcionC == 1) {
                    eliminarLibro()
                } else if (opcionC == 2) {
                    eliminarAutor()
                }
            } while (opcionC != 3)
        }
    } while (opcion != 5)
}
fun registrarLibro (): Boolean {
    // Leer file
    val library = getLibrary();
    // Pedir id Autor
    val scanner = Scanner(System.`in`)
    print("Ingrese el id del autor ")
    val idAutor = scanner.nextLine()
    // Si existe un autor
    library.getAutor(idAutor)?.let {
        val autor = it;
        // Pedir datos del libro
        print("Ingrese el nombre del libro ")
        val nombreLibro = scanner.nextLine()
        print("Ingrese la fecha de publicación del libro ")
        val fechaPublicacion = scanner.nextLine()
        print("Ingrese el precio del libro ")
        val precio = scanner.nextDouble()
        print("Ingrese el número de copias del libro ")
        val numeroCopias = scanner.nextInt()
        // Crear libro
        val libro = Libro(
            autor,
            nombreLibro,
            Utils.stringToDate(fechaPublicacion),
            true,
            precio,
            numeroCopias,
        )
        library.agregarLibro(libro, autor.getUniqueID());
        getGestorDocumentos().writeFile(library.toString());
        return true;
    }
    return false;
}
fun registrarAutor () {
    val scanner = Scanner(System.`in`)
    println("Ingrese el nombre del autor")
    val nombreAutor = scanner.nextLine()
    println("Ingrese la fecha de nacimiento del autor")
    val fechaNacimiento = scanner.nextLine()
    println("Ingrese el numero de publicaciones del autor")
    val numPublicaciones = scanner.nextLine()
    val autor = Autor(nombreAutor, Utils.stringToDate(fechaNacimiento), numPublicaciones.toInt())
    val library = getLibrary();
    library.agregarAutor(autor)
    getGestorDocumentos().writeFile(library.toString());

}
fun actualizarLibro(): Boolean{
    // Leer file
    // Path directory
    val library = getLibrary();
    // Pedir id Libro
    val scanner = Scanner(System.`in`)
    println("Ingrese el id del libro a actualizar")
    val idLibro = scanner.nextLine()
    library.getLibro(idLibro)?.let {
        val libroActual = it;
        // Pedir datos del libro
        println("Ingrese el nombre del libro ")
        val nombreLibro = scanner.nextLine()
        println("Ingrese la fecha de publicación del libro ")
        val fechaPublicacion = scanner.nextLine()
        println("Ingrese el precio del libro ")
        val precio = scanner.nextDouble()
        println("Ingrese el número de copias del libro ")
        val numeroCopias = scanner.nextInt()
        println("Es un best seller? TRUE or FALSE")
        val bestSeller = scanner.nextBoolean()
        // Crear libro
        val libro = Libro(
            libroActual.getAutor(),
            nombreLibro,
            Utils.stringToDate(fechaPublicacion),
            bestSeller,
            precio,
            numeroCopias,
            id = libroActual.getUniqueID()
        )
        library.actualizarLibro(libro)
        getGestorDocumentos().writeFile(library.toString());
        return true;
    }
    return false;
}
fun actualizarAutor() {
    val library = getLibrary();
    // Pedir id Autor
    val scanner = Scanner(System.`in`)
    println("Ingrese el id del autor a actualizar")
    val idAutor = scanner.nextLine()
    library.getAutor(idAutor)?.let {
        val autorActual = it;
        // Pedir datos del autor
        println("Ingrese el nombre del autor ")
        val nombreAutor = scanner.nextLine()
        println("Ingrese la fecha de nacimiento del autor ")
        val fechaNacimiento = scanner.nextLine()
        println("Ingrese el número de publicaciones del autor ")
        val numPublicaciones = scanner.nextInt()
        // Crear autor
        val autor = Autor(
            nombreAutor,
            Utils.stringToDate(fechaNacimiento),
            numPublicaciones,
            id = autorActual.getUniqueID()
        )
        library.actualizarAutor(autor)
        getGestorDocumentos().writeFile(library.toString());
    }
}
fun eliminarLibro() {
    // Leer file
    val library = getLibrary();
    // Pedir id Libro
    val scanner = Scanner(System.`in`)
    print("Ingrese el id del libro a eliminar")
    val idLibro = scanner.nextLine()
    library.eliminarLibro(idLibro)
    getGestorDocumentos().writeFile(library.toString());
}
fun eliminarAutor() {
    // Leer file
    val library = getLibrary();
    // Pedir id Autorln
    val scanner = Scanner(System.`in`)
    println("Ingrese el id del autor a eliminar")
    val idAutor = scanner.nextLine()
    library.eliminarAutor(idAutor)
    getGestorDocumentos().writeFile(library.toString());
}
fun getLibrary (): GestorLibros {
    val gestorLibros: GestorLibros = GestorLibros();
    gestorLibros.setLibrosAutores(getGestorDocumentos().readFile())
    return gestorLibros;
}
fun getGestorDocumentos (): GestorDocumentos {
    val path = System.getProperty("user.dir")
    val gestorDocumentos = GestorDocumentos("$path\\src\\main\\resources\\Autor.txt")
    return gestorDocumentos;
}