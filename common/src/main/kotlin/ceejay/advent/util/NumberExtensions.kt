package ceejay.advent.util

fun Int.isEven() = this and 1 == 0
fun Int.isOdd() = !isEven()
infix fun Int.isDivisibleBy(divisor: Int) = this % divisor == 0

infix fun Long.isDivisibleBy(divisor: Long) = this % divisor == 0L

fun UInt.isEven() = this and 1u == 0u