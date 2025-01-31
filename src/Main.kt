import kotlin.math.pow

class Persona(var peso: Double, var altura: Double) {

    var nombre: String = ""

    val imc: Double = peso / (altura.pow(2.0))

    constructor(peso: Double, altura: Double, nombre: String) : this(peso, altura) {
        this.nombre = nombre
    }

    fun saludar() :String{return "Hola soy $nombre"}

    private fun alturaEncimaMedia() :Boolean{return altura >= 1.75}

    private fun pesoEncimaMedia(): Boolean {return peso >= 70.0}

    private fun obtenerDescImc() :String{
        return when {
            imc < 18.5 -> "Peso insuficiente"
            imc in 18.5..24.9 -> "Peso saludable"
            imc in 25.0..29.9 -> "Sobrepeso"
            else -> "Obesidad"
        }
    }

    fun obtenerDesc(): String {
        val alturaEstado = if (alturaEncimaMedia()) "Por encima de la media" else "Por debajo de la media"
        val pesoEstado = if (pesoEncimaMedia()) "Por encima de la media" else "Por debajo de la media"
        val imcDesc = obtenerDescImc()

        return "$nombre con una altura de ${"%.2f".format(altura)}m ($alturaEstado) y un peso de ${"%.1f".format(peso)}kg ($pesoEstado) tiene un IMC de ${"%.2f".format(imc)} ($imcDesc)."
    }


    override fun toString(): String {
        return "Nombre: $nombre\n -> Peso: $peso kg\n -> Altura: $altura m\n -> IMC: %.2f".format(imc)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Persona) return false
        return peso == other.peso && altura == other.altura && nombre == other.nombre
    }

    override fun hashCode(): Int {
        var result = peso.hashCode()
        result = 31 * result + altura.hashCode()
        result = 31 * result + nombre.hashCode()
        return result
    }
}

fun pedirNombre(): String {
    print("Dime tu nombre: ")
    val nombre = readln().trim()

    return nombre.ifBlank {
        println("ERROR, intentelo de nuevo.")
        pedirNombre()
    }
}

fun main() {

    val persona1 = Persona(60.0, 1.75)
    persona1.nombre = pedirNombre()

    val persona2 = Persona(75.0, 1.80, "Jose Mari")
    val persona3 = Persona(90.0, 1.85, "Mafita")

    println("\nDatos de las personas creadas:")
    println(persona1)
    println(persona2)
    println(persona3)

    persona3.altura = 1.80
    println("\nPersona 3 después del cambio de altura - Peso: ${persona3.peso}, Altura: ${persona3.altura}, IMC: ${"%.2f".format(persona3.imc)}")

    persona2.altura = persona3.altura

    println("\nPersona 2 después del cambio de altura:")
    println(persona2)
    println("\nPersona 3:")
    println(persona3)

    println("\n¿Persona 2 y Persona 3 son iguales? ${persona2 == persona3}")

    val personas = listOf(
        persona1,
        persona2,
        persona3,
        Persona(50.0, 1.60, "Lucía"),
        Persona(100.0, 1.90, "Martín")
    )

    println("\n--- Saludos y descripciones de todas las personas ---\n")
    for (persona in personas) {
        println(persona.saludar())
        println(persona.obtenerDesc())
        println("--------------------------------------------------")
    }
}