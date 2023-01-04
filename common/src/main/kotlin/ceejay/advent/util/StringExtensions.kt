package ceejay.advent.util

fun String?.isInteger(): Boolean {
    if (isNullOrBlank()) {
        return false
    }

    val firstDigit = if (first() == '-' || first() == '+') 1 else 0

    if (length - firstDigit == 0) {
        return false
    }

    for (i in firstDigit until length) {
        if (!this[i].isDigit()) {
            return false
        }
    }
    return true
}