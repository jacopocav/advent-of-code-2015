package ceejay.advent

import ceejay.advent.util.Day
import ceejay.advent.util.indicesOfAll

object `Day 19 - Medicine for Rudolph` : Day<Any, Any>() {
    override val number = 19

    override fun Sequence<String>.doPart1(): Any {
        val (transformations, molecule) = parse()

        val distinctResults = transformations.flatMapTo(mutableSetOf()) { (from, to) ->
            molecule.indicesOfAll(from).map { i ->
                molecule.replaceRange(startIndex = i, endIndex = i + from.length, replacement = to)
            }
        }

        return distinctResults.size
    }

    override fun Sequence<String>.doPart2(): Any {
        val (transformations, target) = parse()

        return with(MoleculeTransformer(transformations)) {
            findShortestTransformationChain("e", target)
        }
    }

    private fun Sequence<String>.parse(): Pair<List<Pair<String, String>>, String> {
        val lines = toList()
        val startingMolecule = lines.last()
        val transformations = lines.dropLast(1).map { it.split("=>") }
            .map { it[0].trim() to it[1].trim() }

        return transformations to startingMolecule
    }
}

