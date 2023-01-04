package ceejay.advent

import ceejay.advent.util.Day

object `Day 9 - All in a Single Night` : Day<Int, Any>() {
    override val number = 9

    override fun Sequence<String>.doPart1(): Int {
        val edges = parseEdges()

        return with(Dijkstra(edges)) {
            shortestPathThroughAllNodes().also { debug(it) }.dist
        }
    }

    override fun Sequence<String>.doPart2(): Any {
        val edges = parseEdges()
        return with(LongestDistanceFinder(edges)) {
            val (path, dist) = find()
            debug(path)
            dist
        }
    }

    private fun Sequence<String>.parseEdges(): Map<String, Map<String, Int>> =
        buildMap<String, MutableMap<String, Int>> {
            for (line in this@parseEdges) {
                val (cities, distanceString) = line.split("=").map { it.trim() }
                val (city1, city2) = cities.split("to").map { it.trim() }
                val distance = distanceString.toInt()

                getOrPut(city1) { mutableMapOf() }[city2] = distance
                getOrPut(city2) { mutableMapOf() }[city1] = distance
            }
        }
}

