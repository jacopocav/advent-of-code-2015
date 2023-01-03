import ceejay.advent.util.Day
import ceejay.advent.util.Input
import ceejay.advent.util.illegal

fun main(args: Array<String>) {
    val (number, day, input) = args.parse()

    println("Running day $number on input ${input.fileName}")
    day.part1(input).also { println("Part 1 result: $it") }
    day.part2(input).also { println("Part 2 result: $it") }
}

private fun Array<String>.parse(): Triple<Int, Day<*, *>, Input> {
    require(isNotEmpty()) { "must pass at least one argument (day number)" }

    val dayNum = first().toIntOrNull()

    val day = dayNum
        ?.let { Day.registry[it] }
        ?: illegal("first argument ${first()} is not a number or is not one of ${Day.registry.keys.sorted()}")

    val input = getOrNull(1)
        ?.lowercase()
        ?.let {
            inputMap[it]
                ?: illegal("invalid second argument: $it. Valid arguments are ${inputMap.keys}")
        }
        ?: Input.Real

    return Triple(dayNum, day, input)
}

private val inputMap = mapOf(
    "example" to Input.Example,
    "real" to Input.Real,
)