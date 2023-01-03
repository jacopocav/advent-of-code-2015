package ceejay.advent

import ceejay.advent.util.*
import ceejay.advent.util.Vector2D.Companion.vector
import ceejay.advent.util.Vector2D.Direction.*

object `Day 3 - Perfectly Spherical Houses in a Vacuum` : Day<Int, Any>() {
    override val number = 3

    override fun doPart1(input: Input) = input.withLines {
        val directions = single().parse()
        val destinations = directions
            .runningFold(vector(0, 0)) { pos, dir -> pos move dir }
            .toSet()

        destinations.size
    }

    override fun doPart2(input: Input) = input.withLines {
        val directions = single().parse()
        val santaDirections = directions.filterIndexed { i, _ -> i.isEven() }
        val roboSantaDirections = directions.filterIndexed { i, _ -> i.isOdd() }

        val santaPositions = santaDirections
            .runningFold(vector(0, 0)) { pos, dir -> pos move dir }
        val roboSantaPositions = roboSantaDirections
            .runningFold(vector(0, 0)) { pos, dir -> pos move dir }

        (santaPositions + roboSantaPositions).toSet().size
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

