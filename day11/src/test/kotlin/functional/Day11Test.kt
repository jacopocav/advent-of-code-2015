package functional

import ceejay.advent.`Day 11 - Corporate Policy`
import ceejay.advent.util.Input
import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {
    private val underTest = `Day 11 - Corporate Policy`

    @Test
    fun `Part 1 returns correct result on example input`() {
        // when
        val (actual, _) = underTest.part1(Input.Example)

        // then
        assertEquals("ghjaabcc", actual)
    }

    @Test
    fun `Part 1 returns correct result on real input`() {
        // when
        val (actual, _) = underTest.part1(Input.Real)

        // then
        assertEquals("cqjxxyzz", actual)
    }

    @Test
    fun `Part 2 returns correct result on example input`() {
        // when
        val (actual, _) = underTest.part2(Input.Example)

        // then
        assertEquals("ghjbbcdd", actual)
    }

    @Test
    fun `Part 2 returns correct result on real input`() {
        // when
        val (actual, _) = underTest.part2(Input.Real)

        // then
        assertEquals("cqkaabcc", actual)
    }
}