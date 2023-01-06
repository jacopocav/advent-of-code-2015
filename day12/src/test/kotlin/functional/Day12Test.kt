package functional

import ceejay.advent.`Day 12 - JSAbacusFramework_io`
import ceejay.advent.util.Input
import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {
    private val underTest = `Day 12 - JSAbacusFramework_io`

    @Test
    fun `Part 1 returns correct result on example input`() {
        // when
        val (actual, _) = underTest.part1(Input.Example)

        // then
        assertEquals(15, actual)
    }

    @Test
    fun `Part 1 returns correct result on real input`() {
        // when
        val (actual, _) = underTest.part1(Input.Real)

        // then
        assertEquals(111754, actual)
    }

    @Test
    fun `Part 2 returns correct result on example input`() {
        // when
        val (actual, _) = underTest.part2(Input.Example)

        // then
        assertEquals(0, actual)
    }

    @Test
    fun `Part 2 returns correct result on real input`() {
        // when
        val (actual, _) = underTest.part2(Input.Real)

        // then
        assertEquals(65402, actual)
    }
}