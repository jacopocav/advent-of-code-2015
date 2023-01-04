package ceejay.advent.util

import kotlin.math.abs

@JvmRecord
data class Vector2D(val x: Int, val y: Int) {
    operator fun unaryMinus() = this * -1
    operator fun plus(other: Vector2D) = vector(x = x + other.x, y = y + other.y)
    operator fun minus(other: Vector2D) = vector(x = x - other.x, y = y - other.y)
    operator fun times(factor: Int) = vector(x = x * factor, y = y * factor)
    operator fun div(divisor: Int) = vector(x = x / divisor, y = y / divisor)

    fun flip() = vector(x = y, y = x)



    override fun toString(): String = "($x, $y)"

    /**
     * Computes the Manhattan distance between `this` and [other]
     */
    infix fun manhattan(other: Vector2D): Int = abs(x - other.x) + abs(y - other.y)

    enum class Direction(private val offset: Vector2D) {
        NORTH(vector(0, -1)),
        EAST(vector(1, 0)),
        SOUTH(vector(0, 1)),
        WEST(vector(-1, 0));

        fun move(vector: Vector2D) = vector + offset
    }

    infix fun move(direction: Direction) = direction.move(this)

    companion object {
        fun vector(x: Int, y: Int): Vector2D = Vector2D(x, y)
    }
}

operator fun Int.times(vector: Vector2D) = vector * this