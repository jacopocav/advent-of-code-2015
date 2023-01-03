package functional

import ceejay.advent.`Day 5 - Doesnt He Have Intern-Elves For This`
import ceejay.advent.util.Input
import kotlin.test.Test
import kotlin.test.assertEquals

class Day05Test {
    private val underTest = `Day 5 - Doesnt He Have Intern-Elves For This`

    @Test
    fun `Part 1 returns correct result on example input`() {
        // when
        val (actual, _) = underTest.part1(Input.Example)

        // then
        assertEquals(2, actual)
    }

    @Test
    fun `Part 1 returns correct result on real input`() {
        // when
        val (actual, _) = underTest.part1(Input.Real)

        // then
        assertEquals(255, actual)
    }

    @Test
    fun `Part 2 returns correct result on example input`() {
        // when
        val (actual, _) = underTest.part2(Input.Example)

        // then
        assertEquals(2, actual)
    }

    @Test
    fun `Part 2 returns correct result on real input`() {
        // when
        val (actual, _) = underTest.part2(Input.Real)

        // then
        assertEquals(55, actual)
    }
}