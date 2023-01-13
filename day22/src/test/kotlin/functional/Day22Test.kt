package functional

import ceejay.advent.`Day 22 - Wizard Simulator 20XX`
import ceejay.advent.util.Input
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class Day22Test {
    private val underTest = `Day 22 - Wizard Simulator 20XX`

    @Test
    fun `Part 1 returns correct result on example input`() {
        // when
        val (actual, _) = underTest.part1(Input.Example)

        // then
        assertEquals(641, actual)
    }

    @Test
    fun `Part 1 returns correct result on real input`() {
        // when
        val (actual, _) = underTest.part1(Input.Real)

        // then
        assertEquals(1824, actual)
    }

    @Test
    fun `Part 2 returns correct result on example input`() {
        // when
        val (actual, _) = underTest.part2(Input.Example)

        // then
        assertNull(actual)
    }

    @Test
    fun `Part 2 returns correct result on real input`() {
        // when
        val (actual, _) = underTest.part2(Input.Real)

        // then
        assertEquals(1937, actual)
    }
}