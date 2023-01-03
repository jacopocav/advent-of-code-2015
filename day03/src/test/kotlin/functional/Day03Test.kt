package functional

import ceejay.advent.`Day 3 - Perfectly Spherical Houses in a Vacuum`
import ceejay.advent.util.Input
import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Test {
    private val underTest = `Day 3 - Perfectly Spherical Houses in a Vacuum`

    @Test
    fun `Part 1 returns correct result on example input`() {
        // when
        val (actual, _) = underTest.part1(Input.Example)

        // then
        assertEquals(4, actual)
    }

    @Test
    fun `Part 1 returns correct result on real input`() {
        // when
        val (actual, _) = underTest.part1(Input.Real)

        // then
        assertEquals(2081, actual)
    }

    @Test
    fun `Part 2 returns correct result on example input`() {
        // when
        val (actual, _) = underTest.part2(Input.Example)

        // then
        assertEquals(3, actual)
    }

    @Test
    fun `Part 2 returns correct result on real input`() {
        // when
        val (actual, _) = underTest.part2(Input.Real)

        // then
        assertEquals(2341, actual)
    }
}