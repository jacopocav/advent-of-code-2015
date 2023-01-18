package functional

import ceejay.advent.`Day 23 - Opening the Turing Lock`
import ceejay.advent.util.Input
import kotlin.test.Test
import kotlin.test.assertEquals

class Day23Test {

    private val underTest = `Day 23 - Opening the Turing Lock`

    @Test
    fun `Part 1 returns correct result on example input`() {
        // when
        val (actual, _) = underTest.part1(Input.Example)

        // then
        assertEquals(4u, actual)
    }

    @Test
    fun `Part 1 returns correct result on real input`() {
        // when
        val (actual, _) = underTest.part1(Input.Real)

        // then
        assertEquals(170u, actual)
    }

    @Test
    fun `Part 2 returns correct result on example input`() {
        // when
        val (actual, _) = underTest.part2(Input.Example)

        // then
        assertEquals(2u, actual)
    }

    @Test
    fun `Part 2 returns correct result on real input`() {
        // when
        val (actual, _) = underTest.part2(Input.Real)

        // then
        assertEquals(247u, actual)
    }
}