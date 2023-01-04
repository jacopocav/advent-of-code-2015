package ceejay.advent

import ceejay.advent.util.copy
import ceejay.advent.util.priorityQueueComparing
import java.util.*

class Dijkstra(private val edges: Map<String, Map<String, Int>>) {
    private val nodes: List<String> = (edges.keys + edges.values.flatMap { it.keys }).distinct()

    fun shortestPathThroughAllNodes(): Path {
        var best = longest

        for (node in nodes) {
            val queue = priorityQueue(node)

            while (queue.isNotEmpty()) {
                val path = queue.remove()

                if (path.allVisited() && path.dist < best.dist) {
                    best = path
                    continue
                }

                path.neighbors()
                    .filter { it.current !in path.visited && it.dist <= best.dist }
                    .forEach {
                        queue -= it
                        queue += it
                    }
            }
        }
        return best
    }

    data class Path(
        val current: String,
        val visited: BitSet,
        val dist: Int,
        var prev: Path? = null,
    ) {
        override fun equals(other: Any?): Boolean = other is Path && current == other.current
        override fun hashCode(): Int = current.hashCode()

        override fun toString(): String = generateSequence(this) { it.prev }
            .mapTo(mutableListOf()) { it.current }
            .asReversed()
            .joinToString(" -> ") + " = $dist"

    }

    private fun priorityQueue(node: String, descending: Boolean = false) =
        nodes.mapTo(priorityQueueComparing(descending) { it.dist }) {
            val dist = when {
                it == node -> 0
                descending -> Int.MIN_VALUE
                else -> Int.MAX_VALUE
            }

            Path(it, bitSetOfCity(it), dist)
        }

    private fun bitSetOfCity(city: String) =
        BitSet(nodes.size).also { it[nodes.indexOf(city)] = true }

    private operator fun BitSet.plus(city: String): BitSet =
        copy().also { it.set(nodes.indexOf(city)) }

    private operator fun BitSet.contains(city: String): Boolean = this[nodes.indexOf(city)]

    private fun Path.allVisited() = visited.cardinality() == nodes.size

    private fun Path.neighbors() = edges[current]!!.map { (city, distance) ->
        Path(city, visited + city, dist + distance, this)
    }

    companion object {
        private val longest = Path("<DUMMY>", BitSet(), Int.MAX_VALUE)
    }
}