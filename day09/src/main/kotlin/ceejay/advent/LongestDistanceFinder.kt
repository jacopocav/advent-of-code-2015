package ceejay.advent

import ceejay.advent.util.permutations

class LongestDistanceFinder(private val edges: Map<String, Map<String, Int>>) {
    private val nodes = (edges.keys + edges.values.flatMap { it.keys }).distinct()

    data class Path(val nodes: List<String>, val totalDistance: Int) {
        override fun toString(): String = nodes.joinToString(" -> ") + " = $totalDistance"
    }

    fun find(): Path = nodes.permutations()
        .map { Path(it, it.totalDistance()) }
        .maxBy { it.totalDistance }

    private fun List<String>.totalDistance() =
        zipWithNext { from, to -> edges[from]!![to]!! }.sum()
}