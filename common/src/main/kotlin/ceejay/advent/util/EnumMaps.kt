package ceejay.advent.util

import java.util.*

inline fun <reified T : Enum<T>, V> enumMapOf(): EnumMap<T, V> = EnumMap(T::class.java)
inline fun <reified T : Enum<T>, V> enumMapOfAll(valueInitializer: (T) -> V): EnumMap<T, V> {
    val map = EnumMap<T, V>(T::class.java)
    enumValues<T>().forEach { map[it] = valueInitializer(it) }
    return map
}

inline fun <reified T : Enum<T>, V> enumMapOf(vararg elements: Pair<T, V>): EnumMap<T, V> =
    if (elements.isNotEmpty()) EnumMap(elements.toMap())
    else enumMapOf()

inline fun <reified T : Enum<T>, V> Map<T, V>.toEnumMap(): Map<T, V> = when {
    this is EnumMap -> this
    isNotEmpty() -> EnumMap(this)
    else -> enumMapOf()
}

fun <T: Enum<T>, V> EnumMap<T, V>.copy(): EnumMap<T, V> = EnumMap(this)