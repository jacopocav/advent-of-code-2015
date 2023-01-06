package functional

import ceejay.advent.`Day 10 - Elves Look, Elves Say`
import ceejay.advent.util.Input
import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {

    private val underTest = `Day 10 - Elves Look, Elves Say`

    @Test
    fun `Part 1 returns correct result on example input`() {
        // when
        val (actual, _) = underTest.part1(Input.Example)

        // then
        assertEquals(82350, actual)
    }

    @Test
    fun `Part 1 returns correct result on real input`() {
        // when
        val (actual, _) = underTest.part1(Input.Real)

        // then
        assertEquals(492982, actual)
    }

    @Test
    fun `Part 2 returns correct result on example input`() {
        // when
        val (actual, _) = underTest.part2(Input.Example)

        // then
        assertEquals(1166642, actual)
    }

    @Test
    fun `Part 2 returns correct result on real input`() {
        // when
        val (actual, _) = underTest.part2(Input.Real)

        // then
        assertEquals(6989950, actual)
    }
}