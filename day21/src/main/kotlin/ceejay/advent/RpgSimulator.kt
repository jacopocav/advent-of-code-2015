package ceejay.advent

import ceejay.advent.Item.Companion.dummy
import ceejay.advent.Item.Type
import ceejay.advent.Item.Type.*
import ceejay.advent.util.priorityQueueComparing

class RpgSimulator(items: Iterable<Item>, val player: Character, val boss: Character) {
    init {
        require(player.items.isEmpty()) { "player must start with no items" }
        require(boss.items.isEmpty()) { "boss cannot have any items" }
    }

    private val shop = items.groupBy { it.type }

    data class Path(
        val items: Set<Item>,
        val counters: Map<Type, Int>,
        val cost: Int,
    )

    fun findCheapestWin(): Int {
        val queue = priorityQueueComparing<Path, _> { it.cost }
        queue += Path(setOf(), emptyMap(), 0)

        while (queue.isNotEmpty()) {
            val path = queue.remove()

            if (player.copy(items = path.items) >= boss) {
                // >= is because if player and boss die at the same turn, the player wins
                // because they go first
                return path.items.sumOf { it.cost }
            }

            queue += path.nextItems()
        }
        error("no way found for player to win against boss")
    }

    fun findMostExpensiveLoss(): Int {
        // player has 4 slots: 1 weapon, 1 armor, 2 rings
        // dummy(...) counts as having an empty slot
        val allWeapons = shop[WEAPON]!!
        val allArmors = shop[ARMOR]!! + dummy(ARMOR)
        val allRingPairs = (shop[RING]!! + dummy(RING)).allPairs() + (dummy(RING) to dummy(RING))

        var best = 0

        for (weapon in allWeapons) {
            for (armor in allArmors) {
                for ((ring1, ring2) in allRingPairs) {
                    val set = setOf(weapon, armor, ring1, ring2)
                    val totalCost = set.sumOf { it.cost }
                    if (totalCost <= best) {
                        continue
                    }
                    if (player.copy(items = set) < boss) {
                        best = totalCost
                    }
                }
            }
        }
        return best
    }

    private fun List<Item>.allPairs(): List<Pair<Item, Item>> = buildList {
        val items = this@allPairs
        for (i in 0 until items.size - 1) {
            for (j in i + 1 until items.size) {
                add(items[i] to items[j])
            }
        }
    }

    private fun Path.nextItems(): List<Path> {
        val eligibleItems = if (counters.getOrDefault(WEAPON, 0) == 0) {
            shop[WEAPON]!!
        } else {
            shop.filterKeys { counters.getOrDefault(it, 0) < it.max }
                .values.flatten()
        }

        return eligibleItems
            .filter { it !in items }
            .map { item ->
                val newCounters = counters.toMutableMap()
                    .also { it[item.type] = it.getOrDefault(item.type, 0) + 1 }

                Path(items + item, newCounters, cost + item.cost)
            }
    }
}