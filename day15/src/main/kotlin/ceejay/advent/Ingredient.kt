package ceejay.advent

data class Ingredient(
    val name: String,
    val teaspoon: Teaspoon,
) {
    companion object {
        fun String.parseIngredient(): Ingredient {
            val name = substringBefore(':')
            val properties = substringAfter(':').split(',')
                .map { it.trim().split(' ') }
                .associate { (property, gain) -> property to gain.toInt() }

            return Ingredient(name, Teaspoon.fromMap(properties))
        }


    }
}