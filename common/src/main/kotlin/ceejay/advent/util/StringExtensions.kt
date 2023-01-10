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

fun CharSequence.indicesOfAll(substring: String, ignoreCase: Boolean = false): List<Int> =
    buildList {
        val string = this@indicesOfAll
        var index = string.indexOf(substring, ignoreCase = ignoreCase)

        while (index > -1) {
            add(index)
            index =
                if (index < length - 1) string.indexOf(
                    substring,
                    startIndex = index + 1,
                    ignoreCase
                )
                else -1
        }
    }