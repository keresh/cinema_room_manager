fun convertStringToDouble(input: String): Double {
    return try {
        input.toDouble()
    } catch (e: Exception) {
        return 0.0
    }
}