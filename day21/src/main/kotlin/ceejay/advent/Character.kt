package ceejay.advent

import kotlin.math.ceil
import kotlin.math.max

data class Character(
    val health: Int,
    val baseAttack: Int,
    val baseDefense: Int,
    val items: Set<Item> = emptySet(),
) {
    private val totalAttack = baseAttack + items.sumOf { it.attack }
    private val totalDefense = baseDefense + items.sumOf { it.defense }

    infix operator fun compareTo(defender: Character): Int {
        val attackerLifetime =
            ceil(health.toDouble() / max(0, defender.totalAttack - totalDefense)).toInt()
        val defenderLifeTime =
            ceil(defender.health.toDouble() / max(totalAttack - defender.totalDefense, 1)).toInt()

        return attackerLifetime.compareTo(defenderLifeTime)
    }
}