package ceejay.advent.util

inline fun <T> MutableList<T>.removeWhile(crossinline predicate: (T) -> Boolean): List<T> =
    buildList {
        val outer = this@removeWhile
        while (outer.isNotEmpty() && predicate(outer.first())) {
            add(outer.removeFirst())
        }
    }

fun List<Char>.composeString(): String = joinToString("")

fun <T> Sequence<T>.indicesOfAll(predicate: (T) -> Boolean): Sequence<Int> = withIndex()
    .filter { (_, elem) -> predicate(elem) }
    .map { it.index }

fun Sequence<Int>.product(): Int = fold(1) { a, b -> a * b }

inline fun <T> MutableList<T>.consume(block: (T) -> Unit) {
    while (isNotEmpty()) {
        block(removeFirst())
    }
}

fun <T> mutableDequeOf(): ArrayDeque<T> = ArrayDeque()

fun <T> mutableDequeOf(vararg elements: T): ArrayDeque<T> = ArrayDeque<T>()
    .also { it += elements }

fun <T> mutableDequeOf(elements: Iterable<T>): ArrayDeque<T> = ArrayDeque<T>()
    .also { it += elements }

fun <T> Iterable<T>.toMutableDeque() = toCollection(ArrayDeque())
fun <T> Sequence<T>.toMutableDeque() = toCollection(ArrayDeque())

fun String.toMutableDeque() = toCollection(ArrayDeque())