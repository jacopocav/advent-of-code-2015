import ceejay.advent.util.Day
import ceejay.advent.util.Input
import ceejay.advent.util.illegal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    runBlocking {
        val (day, inputs) = args.parse()
        println("Running $day...")

        inputs.flatMap { input ->
            listOf(
                runPart(1, day, input),
                runPart(2, day, input),
            )
        }.awaitAll()
            .groupBy { it.input }
            .map { it.printResults(day) }
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

private data class Result(val part: Int, val input: Input, val result: Any)

private fun CoroutineScope.runPart(part: Int, day: Day<*, *>, input: Input) =
    async {
        try {
            val result = day.part(part, input)
            Result(part, input, result)
        } catch (e: NotImplementedError) {
            Result(part, input, "<not implemented yet>")
        } catch (e: Exception) {
            Result(part, input, e)
        }
    }

private fun Map.Entry<Input, List<Result>>.printResults(day: Day<*, *>) = let { (input, results) ->
    println("  - file: ${day.number.toString().padStart(2, '0')}-${input.fileName}")
    results.forEach { (part, _, result) ->
        print("    - Part $part result: ")
        if (result is Throwable) {
            result.printStackTrace(System.out)
        } else {
            println(result)
        }
    }
}

private val inputMap = mapOf(
    "example" to Input.Example,
    "real" to Input.Real,
)