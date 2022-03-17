fun main() {
    val index = readLine()!!.toInt()
    val word = readln()
    if (index > word.length - 1 || index < 0) {
        println("There isn't such an element in the given string, please fix the index!")
    } else {
        println(word[index])
    }
}