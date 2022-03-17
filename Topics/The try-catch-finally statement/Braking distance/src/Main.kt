import kotlin.Exception
import kotlin.math.abs

fun calculateBrakingDistance(v1: String, a: String): Int {
    return try {
        v1.toInt() * v1.toInt() / abs(a.toInt() * 2)
    } catch (e: ArithmeticException) {
        println("The car does not slow down!")
        -1
    } catch (e: Exception) {
        println(e.message)
        -1
    }
}