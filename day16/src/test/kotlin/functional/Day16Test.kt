package functional

import ceejay.advent.`Day 16 - Aunt Sue`
import ceejay.advent.util.Input
import kotlin.test.Test
import kotlin.test.assertEquals

class Day16Test {

    @Test
    fun `Part 1 returns correct result on example input`() {
        // when
        val (actual, _) = `Day 16 - Aunt Sue`.part1(Input.Example)

        // then
        assertEquals(1, actual)
    }

    @Test
    fun `Part 1 returns correct result on real input`() {
        // when
        val (actual, _) = `Day 16 - Aunt Sue`.part1(Input.Real)

        // then
        assertEquals(213, actual)
    }

    @Test
    fun `Part 2 returns correct result on example input`() {
        // when
        val (actual, _) = `Day 16 - Aunt Sue`.part2(Input.Example)

        // then
        assertEquals(2, actual)
    }

    @Test
    fun `Part 2 returns correct result on real input`() {
        // when
        val (actual, _) = `Day 16 - Aunt Sue`.part2(Input.Real)

        // then
        assertEquals(323, actual)
    }
}