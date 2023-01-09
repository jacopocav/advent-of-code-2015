package ceejay.advent

import ceejay.advent.util.Rectangle
import ceejay.advent.util.Vector2D
import ceejay.advent.util.Vector2D.Companion.vector
import java.util.*
import kotlin.math.max
import kotlin.math.min

class Grid(
    initialTurnedOn: List<Vector2D>,
    val rectangle: Rectangle,
    private val alwaysOn: Set<Vector2D> = emptySet(),
) {
    private val height = rectangle.bottomRight.y - rectangle.topLeft.y + 1
    private val width = rectangle.bottomRight.x - rectangle.topLeft.y + 1

    private val grid = MutableList(height) { BitSet(width) }

    init {
        require(initialTurnedOn.all { it in rectangle }) { "some lights are not in rectangle $rectangle" }

        for ((x, y) in initialTurnedOn) {
            grid[y][x] = true
        }
        for ((x, y) in alwaysOn) {
            grid[y][x] = true
        }
    }

    fun activeLightsCount() = grid.sumOf { it.cardinality() }

    operator fun get(x: Int, y: Int) = get(vector(x, y))

    operator fun get(position: Vector2D): Boolean =
        position in rectangle && grid[position.y][position.x]

    operator fun contains(position: Vector2D): Boolean = position in rectangle

    operator fun plusAssign(onLights: Set<Vector2D>) {
        for ((x, y) in onLights) {
            grid[y][x] = true
        }
    }

    operator fun minusAssign(offLights: Set<Vector2D>) {
        for ((x, y) in offLights.filter { it !in alwaysOn }) {
            grid[y][x] = false
        }
    }

    fun countNeighbors(pos: Vector2D): Int {
        var count = 0
        for (x in max(rectangle.xMin, pos.x - 1)..min(rectangle.xMax, pos.x + 1)) {
            for (y in max(rectangle.yMin, pos.y - 1)..min(rectangle.yMax, pos.y + 1)) {
                if (pos.x == x && pos.y == y) {
                    continue
                }
                if (grid[y][x]) count++
            }
        }
        return count
    }

    override fun toString(): String {
        val border = "+" + "-".repeat(rectangle.bottomRight.x - rectangle.topLeft.x + 1) + "+"
        return border +
            (rectangle.topLeft.y..rectangle.bottomRight.y).joinToString(
                separator = "\n",
                prefix = "\n",
                postfix = "\n"
            ) { y ->
                (rectangle.topLeft.x..rectangle.bottomRight.x).joinToString(
                    separator = "",
                    prefix = "|",
                    postfix = "|"
                ) { x ->
                    vector(x, y).let {
                        if (this[it]) "#" else " "
                    }
                }
            } + border
    }
}
