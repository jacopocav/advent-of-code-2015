package ceejay.advent.util

import ceejay.advent.util.Vector2D.Companion.vector
import kotlin.collections.Iterator as KIterator

class Rectangle internal constructor(val topLeft: Vector2D, val bottomRight: Vector2D) :
    Collection<Vector2D> {

    override val size: Int =
        if (topLeft.x > bottomRight.x || topLeft.y > bottomRight.y) 0
        else (bottomRight.x - topLeft.x + 1) * (bottomRight.y - topLeft.y + 1)

    fun first() = topLeft
    fun last() = bottomRight

    override fun containsAll(elements: Collection<Vector2D>): Boolean = elements.all { it in this }

    @Suppress("ReplaceSizeZeroCheckWithIsEmpty")
    override fun isEmpty(): Boolean = size == 0

    override operator fun contains(element: Vector2D): Boolean {
        return element.x >= topLeft.x
            && element.y >= topLeft.y
            && element.x <= bottomRight.x
            && element.y <= bottomRight.y
    }

    override fun iterator(): KIterator<Vector2D> = if (isEmpty()) emptyIterator else Iterator()

    private inner class Iterator : KIterator<Vector2D> {
        var hasNext = isNotEmpty()
        var next: Vector2D = topLeft
        override fun hasNext(): Boolean = hasNext

        override fun next(): Vector2D {
            if (!hasNext) {
                throw NoSuchElementException()
            }

            val current = next
            next =
                if (next.x + 1 > bottomRight.x) vector(x = topLeft.x, y = next.y + 1)
                else next.copy(x = next.x + 1)
            hasNext = next in this@Rectangle
            return current
        }
    }

    companion object {
        private val emptyIterator = object : KIterator<Vector2D> {
            override fun hasNext(): Boolean = false
            override fun next(): Vector2D = throw NoSuchElementException()
        }
    }
}

operator fun Vector2D.rangeTo(other: Vector2D) = Rectangle(this, other)
operator fun Vector2D.rangeUntil(other: Vector2D) =
    Rectangle(this, other.copy(x = other.x - 1, y = other.y - 1))