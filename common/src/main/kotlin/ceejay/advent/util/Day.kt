package ceejay.advent.util

abstract class Day<O, T> {

    fun part1(input: Input): TimedResult<O> = timed { doPart1(input) }
    fun part2(input: Input): TimedResult<T> = timed { doPart2(input) }

    protected abstract fun doPart1(input: Input): O
    protected abstract fun doPart2(input: Input): T
}