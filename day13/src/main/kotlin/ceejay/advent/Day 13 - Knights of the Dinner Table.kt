package ceejay.advent

import ceejay.advent.util.Day
import ceejay.advent.util.illegal
import ceejay.advent.util.permutations

private typealias Edges = Map<String, Map<String, Int>>

object `Day 13 - Knights of the Dinner Table` : Day<Int, Any>() {
    override val number = 13

    private const val myself = "Myself"
    private val regex =
        """^(?<subject>\w+) would (?<effect>gain|lose) (?<amount>\d+) happiness units by sitting next to (?<neighbor>\w+).$""".toRegex()

    override fun Sequence<String>.doPart1(): Int {
        val edges = parseEdges()
        return edges.getBestPath().totalHappiness(edges)
    }

    override fun Sequence<String>.doPart2(): Any {
        val edges = parseEdges()
        val bestPath = edges.getBestPath().toMutableList()

        val (_, worst2) = (bestPath + bestPath.first()).zipWithNext()
            .minBy { (first, second) -> edges[first, second] }

        bestPath.add(bestPath.indexOf(worst2), myself)

        return bestPath.totalHappiness(edges)
    }

    private fun Edges.getBestPath(): List<String> {
        return keys.permutations()
            .maxBy { it.totalHappiness(this) }
    }

    private fun Sequence<String>.parseEdges(): Edges =
        buildMap<String, MutableMap<String, Int>> {
            for (line in this@parseEdges) {
                val match = regex.matchEntire(line)
                    ?: illegal("line $line does not match regex ${regex.pattern}")

                val from = match.groups["subject"]!!.value
                val effect = if (match.groups["effect"]!!.value == "gain") 1 else -1
                val amount = effect * match.groups["amount"]!!.value.toInt()
                val to = match.groups["neighbor"]!!.value

                getOrPut(from) { mutableMapOf() }[to] = amount
            }
            assert(all { (_, map) -> map.size == size - 1 }) { "graph is not complete" }
        }

    private fun List<String>.totalHappiness(edges: Edges): Int {
        return zipWithNext { prev, next -> edges[prev, next] }
            .sum() + edges[first(), last()]
    }

    private operator fun Edges.get(from: String, to: String): Int =
        if (from == myself || to == myself) {
            0
        } else {
            this[from]!![to]!! + this[to]!![from]!!
        }
}

