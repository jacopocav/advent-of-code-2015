package ceejay.advent

import ceejay.advent.util.Day
import ceejay.advent.util.Vector2D
import ceejay.advent.util.Vector2D.Companion.vector
import ceejay.advent.util.illegal
import ceejay.advent.util.rangeTo

object `Day 18 - Like a GIF For Your Yard` : Day<Any, Any>(ignoreBlankLines = false) {
    override val number = 18
    private const val defaultSteps = 100

    override fun Sequence<String>.doPart1(): Int {
        return run(cornersAlwaysOn = false)
    }

    override fun Sequence<String>.doPart2(): Int {
        return run(cornersAlwaysOn = true)
    }

    private fun Sequence<String>.run(cornersAlwaysOn: Boolean): Int {
        val (steps, grid) = parse(cornersAlwaysOn)
        debug("---initial configuration---")
        debug(grid)

        repeat(steps) {
            val turnOff = mutableSetOf<Vector2D>()
            val turnOn = mutableSetOf<Vector2D>()

            for (pos in grid.rectangle) {
                when {
                    grid[pos] && grid.countNeighbors(pos) !in 2..3 -> turnOff += pos
                    !grid[pos] && grid.countNeighbors(pos) == 3 -> turnOn += pos
                }
            }

            grid += turnOn
            grid -= turnOff
            debug("")
            debug { "---step ${it + 1}---" }
            debug(grid)
        }

        return grid.activeLightsCount()
    }

    private fun Sequence<String>.parse(cornersAlwaysOn: Boolean): Pair<Int, Grid> {
        val lines = toList()
        val steps =
            if (lines.any { it.isBlank() }) lines.takeWhile { it.isNotBlank() }.single().toInt()
            else defaultSteps
        val rawGrid =
            if (lines.any { it.isBlank() }) lines.dropWhile { it.isNotBlank() }.drop(1)
            else lines
        val rectangle = vector(0, 0)..vector(rawGrid.first().length - 1, rawGrid.size - 1)

        assert(rawGrid.all { it.length == rectangle.bottomRight.x + 1 })


        val onLights = rawGrid
            .flatMapIndexedTo(mutableListOf()) { y, line ->
                line.mapIndexedNotNull { x, char ->
                    when (char) {
                        '#' -> vector(x, y)
                        '.' -> null
                        else -> illegal("unexpected characted: $char")
                    }
                }
            }

        val alwaysOn = if (cornersAlwaysOn) {
            rectangle.run { setOf(topLeft, topRight, bottomLeft, bottomRight) }
        } else emptySet()

        return steps to Grid(onLights, rectangle, alwaysOn)
    }
}

