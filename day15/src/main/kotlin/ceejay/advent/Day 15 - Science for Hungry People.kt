package ceejay.advent

import ceejay.advent.Ingredient.Companion.parseIngredient
import ceejay.advent.Teaspoon.Companion.sum
import ceejay.advent.util.Day

object `Day 15 - Science for Hungry People` : Day<Int, Int>() {
    override val number = 15
    private const val maxTeaspoons = 100
    private const val maxCalories = 500

    override fun Sequence<String>.doPart1(): Int {
        val ingredients = parseIngredients().toList()
        var totalTeaspoons = Teaspoon.zero

        repeat(maxTeaspoons) {
            debug { "teaspoon $it -> $totalTeaspoons" }
            val newScores = ingredients
                .map { it.teaspoon + totalTeaspoons }
                .associateWith { it.score }
            val maxNewScore = newScores.values.max()
            val (bestImprovement, _) = newScores
                .filterValues { it == maxNewScore }
                .maxBy { (teaspoon, _) -> teaspoon.worst }
            totalTeaspoons = bestImprovement
        }

        debug { "teaspoon $maxTeaspoons -> $totalTeaspoons" }
        return totalTeaspoons.score
    }

    override fun Sequence<String>.doPart2(): Int {
        val ingredients = parseIngredients().toList().sortedByDescending { it.teaspoon.calories }

        return ingredients.allValidRecipes(maxTeaspoons, maxCalories)
            .filter { it.totalCalories(ingredients) == maxCalories }
            .map { it.toTeaspoon(ingredients) }
            .maxOf { it.score }
    }

    private fun List<Int>.toTeaspoon(ingredients: List<Ingredient>): Teaspoon {
        return mapIndexed { i, size -> ingredients[i].teaspoon * size }.sum()
    }

    private fun Sequence<String>.parseIngredients() = map { it.parseIngredient() }

    private val Teaspoon.worst: Int
        get() = listOf(capacity, durability, flavor, texture).min()


    private fun List<Ingredient>.allValidRecipes(
        maxTeaspoons: Int,
        maxCalories: Int,
        remainingIngredients: Int = size,
    ): Sequence<List<Int>> = let { ingredients ->
        when {
            maxCalories < 0 -> emptySequence()
            remainingIngredients == 1 -> sequenceOf(listOf(maxTeaspoons))
            else -> sequence {
                val caloriesPerTeaspoon = ingredients[size - remainingIngredients].teaspoon.calories
                val maxValidTeaspoons = maxCalories / caloriesPerTeaspoon
                for (n in 0..maxValidTeaspoons) {
                    yieldAll(
                        allValidRecipes(
                            maxTeaspoons - n,
                            maxCalories - caloriesPerTeaspoon * n,
                            remainingIngredients - 1,
                        ).map { it + n })
                }
            }
        }
    }

    private fun List<Int>.totalCalories(ingredients: List<Ingredient>): Int =
        mapIndexed { i, teaspoons -> ingredients[i].teaspoon.calories * teaspoons }
            .sum()
}

