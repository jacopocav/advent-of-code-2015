package ceejay.advent.util

/**
 * Returns the least common multiple of [a] and [b]
 */
fun lcm(a: Int, b: Int): Int = a * b / gcd(a, b)

/**
 * Returns the least common multiple of [a] and [b]
 */
fun lcm(a: Long, b: Long): Long = a * b / gcd(a, b)

/**
 * Returns the greatest common divisor of [a] and [b]
 */
tailrec fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

/**
 * Returns the greatest common divisor of [a] and [b]
 */
tailrec fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)
