package ceejay.advent

import ceejay.advent.util.Day
import kotlin.math.min

object `Day 20 - Infinite Elves and Infinite Houses` : Day<Int, Any>() {
    override val number = 20

    override fun Sequence<String>.doPart1(): Int {
        val presents = first().toInt()
        return findFirstHouseWithAtLeast(presents, presentsPerHouse = 10)
    }

    override fun Sequence<String>.doPart2(): Any {
        val presents = first().toInt()
        return findFirstHouseWithAtLeast(presents, presentsPerHouse = 11, maxVisitedHouses = 50)
    }

    private fun findFirstHouseWithAtLeast(
        presents: Int,
        presentsPerHouse: Int,
        maxVisitedHouses: Int? = null,
    ): Int {
        // upper limit on number of elves
        // the elf numbered (presents/presentsPerHouse) will deliver the target number of presents
        // on the first house, so he is the worst case result
        val elfLimit = presents / presentsPerHouse
        val houses = IntArray(elfLimit + 1)

        for (elf in 1..elfLimit) {
            var multiple = elf
            while (multiple <= min(elfLimit, maxVisitedHouses?.times(elf) ?: Int.MAX_VALUE)) {
                houses[multiple] += elf * presentsPerHouse
                multiple += elf
            }
        }

        return houses.indexOfFirst { it >= presents }
    }
}