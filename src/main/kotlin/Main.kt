import ceejay.advent.util.Day
import ceejay.advent.util.Input
import ceejay.advent.util.illegal

fun main(args: Array<String>) {
    val (day, inputs) = args.parse()

    inputs.forEach { input ->
        println("Running '$day' on input '${day.filePrefix}${input.fileName}'")

        runCatching { day.part1(input) }
            .onSuccess { println("Part 1 result: $it") }
            .onFailure {
                println("Part 1 failed")
                it.printStackTrace()
            }
        runCatching { day.part2(input) }
            .onSuccess { println("Part 2 result: $it") }
            .onFailure {
                println("Part 2 failed")
                it.printStackTrace()
            }
    }
}

private fun Array<String>.parse(): Pair<Day<*, *>, List<Input>> {
    require(isNotEmpty()) { "must pass at least one argument (day number)" }

    val day = first().toIntOrNull()
        ?.let { Day.registry[it] }
        ?: illegal("first argument ${first()} is not a number or is not one of ${Day.registry.keys.sorted()}")

    val inputs = getOrNull(1)
        ?.lowercase()
        ?.let {
            inputMap[it]
                ?: illegal("invalid second argument: $it. Valid arguments are ${inputMap.keys}")
        }?.let { listOf(it) }
        ?: listOf(Input.Example, Input.Real)

    return day to inputs
}

private val inputMap = mapOf(
    "example" to Input.Example,
    "real" to Input.Real,
)