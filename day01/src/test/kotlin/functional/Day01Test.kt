package functional

import ceejay.advent.`Day 1 - Not Quite Lisp`
import ceejay.advent.util.Input
import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {
    private val underTest = `Day 1 - Not Quite Lisp`

    @Test
    fun `Part 1 returns correct result on example input`() {
        // when
        val (actual, _) = underTest.part1(Input.Example)

        // then
        assertEquals(-3, actual)
    }

    @Test
    fun `Part 1 returns correct result on real input`() {
        // when
        val (actual, _) = underTest.part1(Input.Real)

        // then
        assertEquals(138, actual)
    }

    @Test
    fun `Part 2 returns correct result on example input`() {
        // when
        val (actual, _) = underTest.part2(Input.Example)

        // then
        assertEquals(1, actual)
    }

    @Test
    fun `Part 2 returns correct result on real input`() {
        // when
        val (actual, _) = underTest.part2(Input.Real)

        // then
        assertEquals(1771, actual)
    }
}