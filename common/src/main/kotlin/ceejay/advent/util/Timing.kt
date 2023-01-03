package ceejay.advent.util

import kotlin.time.Duration
import kotlin.time.Duration.Companion.nanoseconds

data class TimedResult<T>(val result: T, val time: Duration) {
    override fun toString(): String {
        return "$result (time: $time)"
    }
}

inline fun <T> timed(block: () -> T): TimedResult<T> {
    val start = System.nanoTime()
    val result = block()
    val end = System.nanoTime()

    return TimedResult(result, (end - start).nanoseconds)
}