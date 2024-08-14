import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    while (true) {
        println("Menú:")
        println("1. Convertir número decimal a binario")
        println("2. Análisis de palabras")
        println("3. Salir")
        print("Elige una opción: ")
        when (scanner.nextInt()) {
            1 -> convertirDecimalABinario(scanner)
            2 -> analizarPalabras(scanner)
            3 -> {
                println("Saliendo...")
                break
            }
            else -> println("Opción no válida, intenta de nuevo.")
        }
    }
}

fun convertirDecimalABinario(scanner: Scanner) {
    print("Ingresa un número decimal: ")
    val numeroDecimal = scanner.nextInt()
    val numeroBinario = Integer.toBinaryString(numeroDecimal)
    println("El número binario es: $numeroBinario")
}

fun analizarPalabras(scanner: Scanner) {
    scanner.nextLine() // Limpiar el buffer
    println("Ingresa varias palabras separadas y separelas por un espacio: ")
    val palabras = scanner.nextLine().split(" ")
    var palindromas = 0
    var vocales2 = 0
    var consonante = 0

    for (palabra in palabras) {
        if (esPalindromo(palabra)) palindromas++
        if (tieneAlMenosDosVocalesDistintas(palabra)) vocales2++
        if (iniciaConConsonante(palabra)) consonante++
    }

    println("Cantidad de palabras palíndromas: $palindromas")
    println("Cantidad de palabras con al menos 2 vocales distintas: $vocales2")
    println("Cantidad de palabras que inician con una consonante: $consonante")
}

fun esPalindromo(palabra: String): Boolean {
    val palabraLimpia = palabra.lowercase().replace(Regex("[^a-z0-9]"), "")
    return palabraLimpia == palabraLimpia.reversed()
}

fun tieneAlMenosDosVocalesDistintas(palabra: String): Boolean {
    val vocales = setOf('a', 'e', 'i', 'o', 'u')
    val vocalesEncontradas = palabra.lowercase().filter { it in vocales }.toSet()
    return vocalesEncontradas.size >= 2
}

fun iniciaConConsonante(palabra: String): Boolean {
    val consonantes = "bcdfghjklmnpqrstvwxyz"
    return  palabra.lowercase()[0] in consonantes
}

