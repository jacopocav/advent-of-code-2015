package ceejay.advent.util

import org.reflections.Reflections
import org.reflections.scanners.Scanners
import org.reflections.util.ConfigurationBuilder

abstract class Day<O, T> {

    fun part1(input: Input): TimedResult<O> = timed { doPart1(input) }
    fun part2(input: Input): TimedResult<T> = timed { doPart2(input) }

    protected abstract fun doPart1(input: Input): O
    protected abstract fun doPart2(input: Input): T

    override fun toString(): String = this::class.simpleName!!

    companion object {
        val registry: Map<Int, Day<*, *>> by lazy {
            val ref = Reflections(
                ConfigurationBuilder()
                    .forPackage("ceejay.advent")
                    .addScanners(Scanners.SubTypes)
            )

            ref.getSubTypesOf(Day::class.java)
                .associate {
                    val number = it.simpleName.substringAfter("Day").toIntOrNull()
                        ?: error("class ${it.simpleName} does not have name in the format Day##")

                    val instance = (it.kotlin.objectInstance
                        ?: error("class ${it.simpleName} is not a singleton object"))

                    number to instance
                }
        }
    }
}