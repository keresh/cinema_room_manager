const val GRAVITY: Double = 9.8

fun main() {
    val density = readln().toDouble()
    val columnHeight = readln().toDouble()
    println(density * GRAVITY * columnHeight)
}