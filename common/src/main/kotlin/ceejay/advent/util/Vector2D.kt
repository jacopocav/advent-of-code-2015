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

    companion object {
        fun vector(x: Int, y: Int): Vector2D = Vector2D(x, y)
    }
}

operator fun Int.times(vector: Vector2D) = vector * this