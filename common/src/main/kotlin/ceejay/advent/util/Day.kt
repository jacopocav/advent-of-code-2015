package ceejay.advent.util

import org.reflections.Reflections
import org.reflections.scanners.Scanners
import org.reflections.util.ConfigurationBuilder

abstract class Day<O, T> : Debuggable {
    abstract val number: Int
    var indent: String = ""
    override var debugEnabled: Boolean = false

    fun part(num: Int, input: Input): TimedResult<out Any?> = when (num) {
        1 -> part1(input)
        2 -> part2(input)
        else -> error("part not supported $num")
    }

    fun part1(input: Input): TimedResult<O> = timed { input.withLines { doPart1() } }
    fun part2(input: Input): TimedResult<T> = timed { input.withLines { doPart2() } }

    protected abstract fun Sequence<String>.doPart1(): O
    protected abstract fun Sequence<String>.doPart2(): T
    val filePrefix get() = "${number.toString().padStart(2, '0')}-"

    private fun <T> Input.withLines(block: Sequence<String>.() -> T) =
        withLines(filePrefix, block = block)

    override fun toString(): String = this::class.simpleName!!

    companion object {
        fun <O, T> Day<O, T>.debug(message: Any) {
            debug { message.toString() }
        }

        inline fun <O, T> Day<O, T>.debug(lazyMessage: () -> String) {
            if (debugEnabled) {
                println(indent + lazyMessage())
            }
        }

        val registry: Map<Int, Day<*, *>> by lazy {
            val ref = Reflections(
                ConfigurationBuilder()
                    .forPackage("ceejay.advent")
                    .addScanners(Scanners.SubTypes)
            )

            ref.getSubTypesOf(Day::class.java)
                .associate {
                    val instance = (it.kotlin.objectInstance
                        ?: error("class ${it.simpleName} is not a singleton object"))

                    instance.number to instance
                }
        }
    }
}