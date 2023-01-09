package ceejay.advent

import ceejay.advent.util.Day

object `Day 16 - Aunt Sue` : Day<Int, Int>() {
    override val number = 16

    override fun Sequence<String>.doPart1(): Int {
        val aunts = map { it.parseSue() }
        val trace = Compounds(
            children = 3,
            cats = 7,
            samoyeds = 2,
            pomeranians = 3,
            akitas = 0,
            vizslas = 0,
            goldfish = 5,
            trees = 3,
            cars = 2,
            perfumes = 1
        )

        return aunts.first { it.compounds == trace }.id
    }

    override fun Sequence<String>.doPart2(): Int {
        val aunts = map { it.parseSue() }

        val fixedTrace = Compounds(
            children = 3,
            samoyeds = 2,
            akitas = 0,
            vizslas = 0,
            cars = 2,
            perfumes = 1
        )

        fun Compounds.matchRange(): Boolean =
            (cats ?: 8) > 7
                && (pomeranians ?: 2) < 3
                && (goldfish ?: 4) < 5
                && (trees ?: 4) > 3

        return aunts.first { it.compounds == fixedTrace && it.compounds.matchRange() }.id
    }

    private fun String.parseSue(): Sue {
        val id = substringBefore(':').substringAfter("Sue ").toInt()
        val compounds = substringAfter(':').split(", ")
            .map { it.split(": ") }
            .associate { (name, quantity) -> name.trim() to quantity.toInt() }

        return Sue(id, Compounds.fromMap(compounds))
    }
}

