package ceejay.advent

import ceejay.advent.Item.Type.*
import ceejay.advent.util.Day
import ceejay.advent.util.illegal

object `Day 21 - RPG Simulator 20XX` : Day<Int, Int>(ignoreBlankLines = false) {
    override val number = 21
    private val defaultPlayer = Character(100, 0, 0)
    private val shopItems = setOf(
        Item(name = "Dagger", type = WEAPON, cost = 8, attack = 4),
        Item(name = "Shortsword", type = WEAPON, cost = 10, attack = 5),
        Item(name = "Warhammer", type = WEAPON, cost = 25, attack = 6),
        Item(name = "Dagger", type = WEAPON, cost = 40, attack = 7),
        Item(name = "Dagger", type = WEAPON, cost = 74, attack = 8),

        Item(name = "Leather", type = ARMOR, cost = 13, defense = 1),
        Item(name = "Chainmail", type = ARMOR, cost = 31, defense = 2),
        Item(name = "Splintmail", type = ARMOR, cost = 53, defense = 3),
        Item(name = "Bandedmail", type = ARMOR, cost = 75, defense = 4),
        Item(name = "Platemail", type = ARMOR, cost = 102, defense = 5),

        Item(name = "Damage +1", type = RING, cost = 25, attack = 1),
        Item(name = "Damage +2", type = RING, cost = 50, attack = 2),
        Item(name = "Damage +3", type = RING, cost = 100, attack = 3),
        Item(name = "Defense + 1", type = RING, cost = 20, defense = 1),
        Item(name = "Defense + 2", type = RING, cost = 40, defense = 2),
        Item(name = "Defense + 3", type = RING, cost = 80, defense = 3),
    )

    override fun Sequence<String>.doPart1(): Int {
        val (player, boss) = parse()

        return with(RpgSimulator(items = shopItems, player = player, boss = boss)) {
            findCheapestWin()
        }
    }

    override fun Sequence<String>.doPart2(): Int {
        val (player, boss) = parse()

        return with(RpgSimulator(items = shopItems, player = player, boss = boss)) {
            findMostExpensiveLoss()
        }
    }

    private fun Sequence<String>.parse(): Pair<Character, Character> {
        val lines = toList()

        val boss = lines.takeWhile { it.isNotBlank() }
            .parseCharacter()

        val player = if (lines.any { it.isBlank() }) {
            lines.takeLastWhile { it.isNotBlank() }
                .parseCharacter()
        } else {
            defaultPlayer
        }

        return player to boss
    }

    private fun List<String>.parseCharacter(): Character {
        val characterMap = map { it.split(":") }
            .associate { (first, second) -> first.trim() to second.trim().toInt() }

        return Character(
            health = characterMap["Hit Points"] ?: illegal("'Hit Points' not found in input"),
            baseAttack = characterMap["Damage"] ?: illegal("'Damage' not found in input"),
            baseDefense = characterMap["Armor"] ?: illegal("'Armor' not found in input"),
        )
    }

}

