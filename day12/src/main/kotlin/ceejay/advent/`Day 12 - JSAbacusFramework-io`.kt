package ceejay.advent

import ceejay.advent.util.Day

object `Day 12 - JSAbacusFramework_io` : Day<Any, Any>() {
    override val number = 12
    private val numRegex = """[+-]?\d+""".toPattern()
    private val braces = charArrayOf('{', '}')

    override fun Sequence<String>.doPart1(): Int =
        sumOf { it.sumContainedIntegers() }

    override fun Sequence<String>.doPart2(): Any =
        sumOf { it.removeJsonObjectsContaining(":\"red\"").sumContainedIntegers() }

    private fun String.removeJsonObjectsContaining(target: String): String {
        val stripped = filterNot { it.isWhitespace() }

        var new = stripped
        var i = 0
        while (i < new.length) {
            val red = new.indexOf(target, 0)
            if (red < 0) {
                break
            }
            val opening = new.indexOfOpeningBrace(red)
            val closing = new.indexOfClosingBrace(red + target.length)

            i = closing + 1

            new = new.substring(0, opening) +
                if (i < length) new.substring(i) else ""
        }
        return new
    }

    private fun String.sumContainedIntegers(): Int {
        val matcher = numRegex.matcher(this)
        var sum = 0
        while (matcher.find()) {
            sum += matcher.group().toInt()
        }
        return sum
    }

    private fun String.indexOfClosingBrace(startIndex: Int): Int {
        var i = startIndex
        var opened = 1
        var closing = -1
        while (opened > 0) {
            closing = indexOfAny(braces, startIndex = i)
            opened += when (this[closing]) {
                '{' -> 1
                '}' -> -1
                else -> 0
            }
            i = closing + 1
        }
        return closing
    }

    private fun String.indexOfOpeningBrace(startIndex: Int): Int {
        var i = startIndex
        var closed = 1
        var opening = -1
        while (closed > 0) {
            opening = lastIndexOfAny(braces, startIndex = i)
            closed += when (this[opening]) {
                '{' -> -1
                '}' -> 1
                else -> 0
            }
            i = opening - 1
        }
        return opening
    }
}

