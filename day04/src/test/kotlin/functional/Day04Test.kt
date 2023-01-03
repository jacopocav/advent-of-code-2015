package functional

import ceejay.advent.`Day 4 - The Ideal Stocking Stuffer`
import ceejay.advent.util.Input
import kotlin.test.Test
import kotlin.test.assertEquals

class Day04Test {

    @Test
    fun `Part 1 returns correct result on example input`() {
        // when
        val (actual, _) = `Day 4 - The Ideal Stocking Stuffer`.part1(Input.Example)

        // then
        assertEquals(609043, actual)
    }

    @Test
    fun `Part 1 returns correct result on real input`() {
        // when
        val (actual, _) = `Day 4 - The Ideal Stocking Stuffer`.part1(Input.Real)

        // then
        assertEquals(346386, actual)
    }

    @Test
    fun `Part 2 returns correct result on example input`() {
        // when
        val (actual, _) = `Day 4 - The Ideal Stocking Stuffer`.part2(Input.Example)

        // then
        assertEquals(6742839, actual)
    }

    @Test
    fun `Part 2 returns correct result on real input`() {
        // when
        val (actual, _) = `Day 4 - The Ideal Stocking Stuffer`.part2(Input.Real)

        // then
        assertEquals(9958218, actual)
    }
}