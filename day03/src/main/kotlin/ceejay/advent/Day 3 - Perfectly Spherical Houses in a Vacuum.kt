package ceejay.advent

import ceejay.advent.util.Day
import ceejay.advent.util.Vector2D.Companion.vector
import ceejay.advent.util.Vector2D.Direction.*
import ceejay.advent.util.illegal
import ceejay.advent.util.isEven
import ceejay.advent.util.isOdd

object `Day 3 - Perfectly Spherical Houses in a Vacuum` : Day<Int, Int>() {
    override val number = 3

    override fun Sequence<String>.doPart1(): Int {
        val directions = single().parse()
        val destinations = directions
            .runningFold(vector(0, 0)) { pos, dir -> pos move dir }
            .toSet()

        return destinations.size
    }

    override fun Sequence<String>.doPart2(): Int {
        val directions = single().parse()

        val santaPositions = directions.filterIndexed { i, _ -> i.isEven() }
            .runningFold(vector(0, 0)) { pos, dir -> pos move dir }

        val roboSantaPositions = directions.filterIndexed { i, _ -> i.isOdd() }
            .runningFold(vector(0, 0)) { pos, dir -> pos move dir }

        return (santaPositions.toSet() + roboSantaPositions).size
    }

    private fun String.parse() = map {
        when (it) {
            '^' -> NORTH
            '>' -> EAST
            'v' -> SOUTH
            '<' -> WEST
            else -> illegal("unexpected character: $it")
        }
    }
}

