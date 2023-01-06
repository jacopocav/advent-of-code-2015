package ceejay.advent

import ceejay.advent.util.Day

object `Day 11 - Corporate Policy` : Day<String, Any>() {
    override val number = 11


    override fun Sequence<String>.doPart1(): String {
        return findNextValid(single().increment())
    }

    override fun Sequence<String>.doPart2(): String {
        val firstValid = doPart1()
        return findNextValid(firstValid.increment())
    }

    private fun findNextValid(password: String): String {
        var next = password
        debug(next)
        while (!next.isValid()) {
            val forbiddenIndex = next.indexOfFirst { it in forbiddenChars }

            next = if (forbiddenIndex >= 0) {
                (next.substring(0, forbiddenIndex) + (next[forbiddenIndex] + 1))
                    .padEnd(next.length, 'a')
            } else {
                next.increment()
            }

            debug(next)
        }
        return next
    }

    private fun String.increment(): String {
        val chars = toCharArray()
        var carryOver = 1
        for (i in chars.indices.reversed()) {
            if (carryOver == 0) {
                break
            }

            chars[i] = chars[i] + carryOver

            carryOver = if (chars[i] > 'z') {
                chars[i] = 'a'
                1
            } else {
                0
            }
        }
        return chars.concatToString()
    }

    private val forbiddenChars = setOf('i', 'o', 'l')

    private fun String.isValid(): Boolean = none { it in forbiddenChars }
        && windowed(3, 1).any { three -> three[0] == three[1] - 1 && three[1] == three[2] - 1 }
        && indexOfFirstPair()?.let { substring(it + 1).indexOfFirstPair(this[it]) } != null


    private fun String.indexOfFirstPair(exceptChar: Char = '!'): Int? {
        for (i in 0 until length - 1) {
            if (this[i] != exceptChar && this[i] == this[i + 1]) {
                return i
            }
        }
        return null
    }
}

