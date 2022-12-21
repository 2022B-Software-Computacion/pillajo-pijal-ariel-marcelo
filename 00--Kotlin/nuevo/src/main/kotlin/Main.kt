import java.util.*

// Main.kt
fun main(){
    println ("Hola")

    // INMUTABLES ( NO Re Asignar )
    val inmutable: String = "Ariel"

    // MUTABLES  ( Re Asignar )
    var mutable: String = "Vicente"
    mutable = "Marcelo"

    // VAR > VAR


    // Sintzxis Duck typing
    val ejemploVariable = "Ejemplo"
    val edadEjemplo: Int = 12
    ejemploVariable.trim() //Obtener caracteres de un String , si no hay argumentos recorta los espacios en blanco

    // Variables primitivas
    val nombreProfesor: String = "Ariel Pillajo"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'S'
    val mayorEdad: Boolean = true
    // Clases JAVA
    val fechaNacimiento: Date = Date()

    // if
    if (true){

    }else {}
    // switch no existe pero...

    val estadoCivilWhen = "S"
    when (estadoCivilWhen) {
        ("S") -> {
            println("acercarse")
        }
        "C" -> {
            println("alejarse")
        }

        "UN" -> println("hablar")
        else -> println("No reconocido")

    }

    val coqueteo = if (estadoCivilWhen == "S") "SI" else "NO"
    println(coqueteo)

    // void imprimir Nombre ( String nombre ) {}
    // Unit == void
    fun calcularSueldo(
        sueldo: Double, // Requerido
        tasa: Double = 12.0, // Optional (Defecto)
        bonoEspecial: Double? = null, // Optional puede ser null o double
    ): Double {
        // Strin -> String?
        // Int -> Int?
        // Date -> Date?
        if (bonoEspecial == null){
            return sueldo * (100 / tasa )
        } else {
            return sueldo * (100 / tasa) + bonoEspecial
        }
    }
}

abstract class NumerosJava {
    protected val numeroUno: Int
    private val numeroDos: Int

    constructor(
        uno: Int,
        dos: Int,
    ){
        this.numeroUno = uno;
        this.numeroDos = dos;
    }
}
// Otra forma de crear un contructor e inicializar variables es usando init
abstract  class Numeros (
    //uno: Int // parametro
    public var numerouno: Int, // Propiedad de clase publica, puede ser protected o private
    var dos: Int
    ) {

    init {
        //uno
        //this.uno
        println("Init")
    }

}

class  Suma (
    numeroUno: Int,
    numeroDos: Int,
): Numeros(uno, dos){
    init {
        this.uno
        this.dos
    }
    constructor(
        uno: Int?,
        dos: Int,
    ):this (// ESTE ES UN CONTRUCTOR SECUNDARIO QUE LLAMA AL PRIMARIO (INIT) Y SI NO RECIBE UN NULL ENTONCES
        // manda un 0 y si no le asigna el valor de uno , el 2 siempre recibe así que le asigna
        if (uno == null) 0 else uno,
        dos
    ) {}

    public fun sumar(): Int {

        // Arreglo estático
        val arregloEstatico: Array<Int>  = arrayOf<Int> (1,2,3)

        // Arreglos dinámicos
        val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1,2,3,4,5)
        println(arregloDinamico)
        arregloDinamico.add(11)
        println(arregloDinamico)

        // Operadores -> Sirven para los arreglos estáticos y dinámicos

        // FOR EACH -> Unit
        // Iterar Arreglo
        val respuestaForEach: Unit =  arregloDinamico
            .forEach {
                valorActual: Int ->
                    println("Valor Actual ${valorActual}")
        }

        // Map -> Muta el arreglo
        // 1) Enviamos el nuevo valor de la iteración
        // 2) NUEVO arrelgo con valores modificados

        val respuestaMap: List<Double> = arregloDinamico
            .map { valorActual: Int ->
                return@map valorActual.toDouble() + 100.00
            }
        // it es como la iteración actual
        val requestMapDos = arregloDinamico.map {it + 15}


        arregloEstatico
            .forEachIndexed {indice: Int, valorActual: Int ->
                println("Valor ${valorActual} Indice: ${indice}")
            }
        println(respuestaForEach)


        // Filter -- Filtrar un arreglo
        // 1) Devolver una expresion ( TRUE o FALSE)
        // 2) Nuevo arreglo filtrado
        val respuestaFilter: List<Int> = arregloDinamico
            .filter { valorActual: Int ->
                val mayoresCinco: Boolean = valorActual > 5
                return@filter mayoresCinco
            }

        val respuestaFilter2 = arregloDinamico.filter {it > 5}
        println(respuestaFilter)
        println(respuestaFilter2)

        // OR AND
        // OR -> ANY  ( Alguno Cumplen )
        // AND -> ALL  (Todos Cumplen )
        val respuestaAny: Boolean = arregloDinamico
            .any{ valorActual: Int ->
                return@any (valorActual > 5)
            }
        println(respuestaAny) // TRUE

        val respuestaAll: Boolean = arregloDinamico
            .all { valorActual: Int ->
                return@all (valorActual > 5)
            }
        println(respuestaAll) // false


        // REDUCE -> VALOR ACUMULADO
        // VALOR ACUMULADO = 0 , AUNQUE PUEDE CAMBIAR
        // [1,2,3,4 ]
        // VALOR1 = VALOR_ACUMULADO + 1 = 1
        // VALOR2 = VALOR1 + 2 = 3
        // VALOR3 = VALOR2 + 3 = 6 ...
        val respuestaReduce: Int = arregloDinamico
            .reduce{
                acumulado: Int, valorActual: Int ->
                return@reduce (acumulado + valorActual)
            }

        return numeroUno + numeroDos;
    }





}



