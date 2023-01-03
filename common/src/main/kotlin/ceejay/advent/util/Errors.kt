package ceejay.advent.util

fun illegal(message: Any): Nothing {
    throw IllegalArgumentException(message.toString())
}