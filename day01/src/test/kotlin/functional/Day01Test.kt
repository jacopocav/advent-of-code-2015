package functional

import ceejay.advent.Day01
import ceejay.advent.util.Input
import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {

    @Test
    fun `Part 1 returns correct result on example input`() {
        // when
        val (actual, _) = Day01.part1(Input.Example)

        // then
        assertEquals(-3, actual)
    }

    @Test
    fun `Part 1 returns correct result on real input`() {
        // when
        val (actual, _) = Day01.part1(Input.Real)

        // then
        assertEquals(138, actual)
    }

    @Test
    fun `Part 2 returns correct result on example input`() {
        // when
        val (actual, _) = Day01.part2(Input.Example)

        // then
        assertEquals(0, actual)
    }

    @Test
    fun `Part 2 returns correct result on real input`() {
        // when
        val (actual, _) = Day01.part2(Input.Real)

        // then
        assertEquals(1771, actual)
    }
}