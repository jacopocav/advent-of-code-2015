package ceejay.advent

import ceejay.advent.util.permutations

class LongestDistanceFinder(private val edges: Map<String, Map<String, Int>>) {
    private val nodes = (edges.keys + edges.values.flatMap { it.keys }).distinct()

    fun find(): Pair<List<String>, Int> {
        val path = nodes.permutations().maxBy { it.totalDistance() }
        return path to path.totalDistance()
    }

    private fun List<String>.totalDistance() =
        zipWithNext { from, to -> edges[from]!![to]!! }.sum()
}