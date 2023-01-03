package ceejay.advent

import ceejay.advent.util.Day

object `Day 2 - I Was Told There Would Be No Math` : Day<Int, Int>() {
    override val number = 2

    override fun Sequence<String>.doPart1(): Int = map { it.toBox() }
        .sumOf { box -> box.surface() + box.smallestSurface() }

    override fun Sequence<String>.doPart2(): Int = map { it.toBox() }
        .sumOf { box -> box.smallestPerimeter() + box.volume() }

    private data class Box(val length: Int, val width: Int, val height: Int) {
        private val smallestTwoSides = listOf(length, width, height).sorted().take(2)
        fun surface() = 2 * length * width + 2 * length * height + 2 * width * height
        fun smallestSurface() = smallestTwoSides.reduce { a, b -> a * b }

        fun smallestPerimeter() = smallestTwoSides.sum() * 2

        fun volume() = length * width * height
    }

    private fun String.toBox() =
        split("x").let { (l, w, h) -> Box(l.toInt(), w.toInt(), h.toInt()) }
}

