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
        // upper limit on number of elves and houses:
        //   - the elf numbered (presents/presentsPerHouse) will deliver the target number of presents
        //     on the first house, so there's no need to compute any further elf
        //   - the house numbered (presents/presentsPerHouse) is guaranteed to have more than
        //     the target number of presents, so it's the worst case result
        val upperLimit = presents / presentsPerHouse
        val houses = IntArray(upperLimit + 1)

        for (elf in 1..upperLimit) {
            var multiple = elf
            val multipleLimit = min(upperLimit, maxVisitedHouses?.times(elf) ?: Int.MAX_VALUE)

            while (multiple <= multipleLimit) {
                houses[multiple] += elf * presentsPerHouse
                multiple += elf
            }
        }

        return houses.indexOfFirst { it >= presents }
    }
}