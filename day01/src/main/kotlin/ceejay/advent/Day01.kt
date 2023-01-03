package ceejay.advent

import ceejay.advent.util.Day
import ceejay.advent.util.Input
import ceejay.advent.util.illegal

object `Day 1 - Not Quite Lisp` : Day<Int, Int>() {
    override val number = 1

    override fun doPart1(input: Input) = input.withLines {
        single().parse().sum()
    }

    override fun doPart2(input: Input): Int = input.withLines {
        val numbers = single().parse()
        var accumulator = 0

        for ((i, v) in numbers.withIndex()) {
            accumulator += v
            if (accumulator == -1) {
                return@withLines i + 1
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

