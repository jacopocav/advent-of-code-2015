package ceejay.advent

import ceejay.advent.util.Day
import ceejay.advent.util.illegal

object `Day 14 - Reindeer Olympics` : Day<Int, Int>() {
    override val number = 14
    private val regex =
        """^(?<name>\w+) can fly (?<speed>\d+) km/s for (?<flySeconds>\d+) seconds, but then must rest for (?<restSeconds>\d+) seconds.$""".trimMargin()
            .toRegex()

    override fun Sequence<String>.doPart1(): Int {
        val reindeers = map { it.parse() }
        val time = 2503

        return reindeers.maxOf { it[time] }
    }

    override fun Sequence<String>.doPart2(): Int {
        val reindeers = map { it.parse() }.toList()
        val time = 2503

        val scores = reindeers.associateWithTo(mutableMapOf()) { 0 }

        for (second in 1..time) {
            debug { "---SECOND $second---" }
            val best = reindeers.maxOf { it[second] }
            scores.forEach { (reindeer, _) ->
                if (reindeer[second] == best) {
                    debug { "${reindeer.name} at ${reindeer[second]}km gains one point" }
                    scores[reindeer] = scores[reindeer]!! + 1
                }
            }
        }

        return scores.values.max()
    }

    private fun String.parse(): Reindeer {
        val groups = regex.matchEntire(this)?.groups
            ?: illegal("line '$this' does not match regex ${regex.pattern}")

        return Reindeer(
            name = groups["name"]!!.value,
            kmsPerSecond = groups["speed"]!!.value.toInt(),
            flightSeconds = groups["flySeconds"]!!.value.toInt(),
            restSeconds = groups["restSeconds"]!!.value.toInt(),
        )
    }
}

private data class Reindeer(
    val name: String,
    val kmsPerSecond: Int,
    val flightSeconds: Int,
    val restSeconds: Int,
) {
    val kmsPerPeriod = kmsPerSecond * flightSeconds
    val period = flightSeconds + restSeconds

    operator fun get(time: Int): Int {
        val fullPeriodKms = (time / period * kmsPerPeriod)
        val remainingSeconds = time % period
        val lastPeriodKms = if (remainingSeconds >= flightSeconds) {
            kmsPerPeriod
        } else {
            remainingSeconds * kmsPerSecond
        }
        return fullPeriodKms + lastPeriodKms
    }
}

