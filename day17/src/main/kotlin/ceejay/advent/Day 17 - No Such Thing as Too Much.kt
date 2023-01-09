package ceejay.advent

import ceejay.advent.util.Day
import ceejay.advent.util.prependTo

object `Day 17 - No Such Thing as Too Much` : Day<Int, Any>(ignoreBlankLines = false) {
    override val number = 17
    private const val defaultLiters = 150

    override fun Sequence<String>.doPart1(): Int {
        val (liters, containers) = parse()
        return containers.findAllContainerCombinations(liters).onEach { debug(it) }.count()
    }

    override fun Sequence<String>.doPart2(): Any {
        val (liters, containers) = parse()
        val combinations = containers.findAllContainerCombinations(liters).toList()
        val minContainersUsed = combinations.minOf { it.size }
        debug { "Minimum number of containers used to contain $liters liters is $minContainersUsed" }
        return combinations.count { it.size == minContainersUsed }
    }

    private fun Sequence<String>.parse(): Pair<Int, List<Int>> {
        // Added number of liters to first line of input (to differentiate between example and real)
        val lines = toList()
        val liters =
            if (lines.any { it.isBlank() }) lines.takeWhile { it.isNotBlank() }.single().toInt()
            else defaultLiters
        val containers =
            (if (lines.any { it.isBlank() }) lines.dropWhile { it.isNotBlank() }.drop(1)
            else lines)
                .map { it.toInt() }.sortedDescending()

        return liters to containers
    }

    private fun List<Int>.findAllContainerCombinations(remainingLiters: Int): Sequence<List<Int>> {
        val candidates = filter { it <= remainingLiters }
        return when (remainingLiters) {
            0 -> when {
                candidates.isEmpty() -> sequenceOf(emptyList())
                else -> emptySequence()
            }

            else -> sequence {
                for (i in candidates.indices) {
                    when (val container = candidates[i]) {
                        remainingLiters -> yield(listOf(container))
                        else -> {
                            val rest: List<Int> =
                                if (i < candidates.indices.last)
                                    candidates.subList(i + 1, candidates.size)
                                else emptyList()
                            yieldAll(rest.findAllContainerCombinations(remainingLiters - container)
                                .map { container prependTo it })
                        }
                    }
                }
            }
        }
    }
}

