const val DIGITS_COUNT = 4
const val SPACES_COUNT = 3

fun parseCardNumber(cardNumber: String): Long {
    var number: String = ""
    val wrongCardNumber = Exception("Wrong card number")
    if (cardNumber.count { it == ' ' } != SPACES_COUNT) throw wrongCardNumber
    for (el in cardNumber.split(" ")) {
        if (el.filter { it in 'A'..'Z' || it in 'a'..'z' }.length == el.length) {
            throw wrongCardNumber
        }
        if (el.count() != DIGITS_COUNT) throw wrongCardNumber
        number += el
    }
    return number.toLong()
}