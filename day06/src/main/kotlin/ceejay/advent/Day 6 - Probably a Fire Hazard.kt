package ceejay.advent

import ceejay.advent.util.Day
import ceejay.advent.util.Vector2D.Companion.vector
import ceejay.advent.util.illegal
import ceejay.advent.util.rangeTo
import java.util.*
import kotlin.math.max
import kotlin.text.RegexOption.IGNORE_CASE

object `Day 6 - Probably a Fire Hazard` : Day<Int, Any>() {
    override val number = 6

    override fun Sequence<String>.doPart1(): Int {
        val lights = Array(1000) { BitSet(1000) }
        val actions = map { it.parseAction() }

        for (action in actions) {
            for (point in action.rectangle) {
                val row = lights[point.y]
                when (action) {
                    is Toggle -> row.flip(point.x)
                    is TurnOn -> row.set(point.x)
                    is TurnOff -> row.clear(point.x)
                }
            }
        }

        return lights.sumOf { it.cardinality() }
    }

    override fun Sequence<String>.doPart2(): Int {
        val lights = Array(1000) { IntArray(1000) }
        val actions = map { it.parseAction() }

        for (action in actions) {
            for (point in action.rectangle) {
                val row = lights[point.y]
                when (action) {
                    is Toggle -> row[point.x] += 2
                    is TurnOn -> row[point.x]++
                    is TurnOff -> row[point.x] = max(0, row[point.x] - 1)
                }
            }
        }

        return lights.sumOf { it.sum() }
    }


    private val regex =
        "^(?<action>toggle|turn on|turn off) (?<fromX>\\d+),(?<fromY>\\d+) through (?<toX>\\d+),(?<toY>\\d+)$"
            .toRegex(IGNORE_CASE)

    private fun String.parseAction(): Action {
        val match = regex.matchEntire(this) ?: illegal("string $this does not match regex $regex")

        val action = match.groups["action"]!!.value.lowercase()

        val from = run {
            val fromX = match.groups["fromX"]!!.value.toInt()
            val fromY = match.groups["fromY"]!!.value.toInt()

            vector(fromX, fromY)
        }
        val to = run {
            val toX = match.groups["toX"]!!.value.toInt()
            val toY = match.groups["toY"]!!.value.toInt()

            vector(toX, toY)
        }

        val rectangle = from..to


        return when (action) {
            "toggle" -> Toggle(rectangle)
            "turn on" -> TurnOn(rectangle)
            "turn off" -> TurnOff(rectangle)
            else -> error("should not happen")
        }
    }
}

