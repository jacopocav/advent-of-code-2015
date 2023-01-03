package ceejay.advent.util

// Int
fun Int.isEven() = this and 1 == 0
fun Int.isOdd() = !isEven()
infix fun Int.isDivisibleBy(divisor: Int) = this % divisor == 0

// BigInteger
infix fun Long.isDivisibleBy(divisor: Long) = this % divisor == 0L