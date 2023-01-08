package ceejay.advent

import ceejay.advent.util.illegal
import kotlin.math.max

data class Teaspoon(
    val capacity: Int,
    val durability: Int,
    val flavor: Int,
    val texture: Int,
    val calories: Int,
) {
    val score = max(0, capacity) * max(0, durability) * max(0, flavor) * max(0, texture)

    operator fun plus(other: Teaspoon): Teaspoon = Teaspoon(
        capacity = capacity + other.capacity,
        durability = durability + other.durability,
        flavor = flavor + other.flavor,
        texture = texture + other.texture,
        calories = calories + other.calories,
    )

    operator fun times(factor: Int): Teaspoon = Teaspoon(
        capacity = capacity * factor,
        durability = durability * factor,
        flavor = flavor * factor,
        texture = texture * factor,
        calories = calories * factor,
    )


    companion object {
        val zero = Teaspoon(0, 0, 0, 0, 0)
        fun fromMap(properties: Map<String, Int>): Teaspoon = Teaspoon(
            properties["capacity"] ?: propertyMissing("capacity"),
            properties["durability"] ?: propertyMissing("durability"),
            properties["flavor"] ?: propertyMissing("flavor"),
            properties["texture"] ?: propertyMissing("texture"),
            properties["calories"] ?: propertyMissing("calories"),
        )

        private fun propertyMissing(propertyName: String): Nothing {
            illegal("Property '$propertyName' not found")
        }

        fun Iterable<Teaspoon>.sum() = fold(zero) { a, b -> a + b }
    }
}