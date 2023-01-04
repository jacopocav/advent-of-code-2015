package ceejay.advent

import ceejay.advent.util.Day

object `Day 8 - Matchsticks` : Day<Int, Int>() {
    override val number = 8

    private val hexRegex = """\\x[a-f0-9]{2}""".toRegex()

    override fun Sequence<String>.doPart1() = sumOf { it.length - it.unescaped().length }


    override fun Sequence<String>.doPart2() = sumOf { it.escaped().length - it.length }

    private fun String.unescaped(): String = trim('"')
        .replace("\\\"", "\"")
        .replace(hexRegex, "?")
        .replace("\\\\", "\\")

    private fun String.escaped(): String = '"' + replace("\\", "\\\\")
        .replace("\"", "\\\"") + '"'
}

