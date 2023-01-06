package ceejay.advent

import ceejay.advent.util.Day

object `Day 10 - Elves Look, Elves Say` : Day<Int, Any>() {
    override val number = 10

    override fun Sequence<String>.doPart1(): Int {
        val number = single()
        require(number.all { it.isDigit() }) { "string $number is not numeric" }

        return number.lookAndSay(40)
    }

    override fun Sequence<String>.doPart2(): Any {
        val number = single()
        require(number.all { it.isDigit() }) { "string $number is not numeric" }

        return number.lookAndSay(50)
    }

    private fun String.lookAndSay(times: Int): Int {
        var current = this
        debug(current)
        repeat(times) {
            current = current.lookAndSay()
            debug(current)
        }
        return current.length
    }

    private fun String.lookAndSay(): String {
        var i = 0
        val builder = StringBuilder()
        while (i < length) {
            val digit = this[i]
            var count = 1
            i++
            while (i < length && this[i] == digit) {
                i++
                count++
            }
            builder.append(count.toString()).append(digit)
        }
        return builder.toString()
    }
}

