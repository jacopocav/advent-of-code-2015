package ceejay.advent.util

// Int
fun Int.isEven() = isDivisibleBy(2)
fun Int.isOdd() = !isEven()
infix fun Int.isDivisibleBy(divisor: Int) = this % divisor == 0

// BigInteger
infix fun Long.isDivisibleBy(divisor: Long) = this % divisor == 0L