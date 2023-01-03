package ceejay.advent

import ceejay.advent.util.Day
import ceejay.advent.util.illegal

object `Day 1 - Not Quite Lisp` : Day<Int, Int>() {
    override val number = 1

    override fun Sequence<String>.doPart1() = single().parse().sum()

    override fun Sequence<String>.doPart2(): Int {
        val numbers = single().parse()
        var accumulator = 0

        for ((i, v) in numbers.withIndex()) {
            accumulator += v
            if (accumulator == -1) {
                return i + 1
            }
        }

        error("floor -1 is never reached")
    }

    private fun String.parse(): List<Int> = map {
        when (it) {
            '(' -> 1
            ')' -> -1
            else -> illegal("unexpected character: $it")
        }
    }
}

