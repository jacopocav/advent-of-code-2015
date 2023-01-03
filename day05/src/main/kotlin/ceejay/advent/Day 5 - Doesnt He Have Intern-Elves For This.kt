package ceejay.advent

import ceejay.advent.util.Day

object `Day 5 - Doesnt He Have Intern-Elves For This` : Day<Int, Int>() {
    override val number = 5

    private const val vowels = "aeiou"
    private val couples = ('a'..'z').map { it.toString() + it }
    private val badStrings = listOf("ab", "cd", "pq", "xy")

    override fun Sequence<String>.doPart1(): Int = count { string ->
        badStrings.none { it in string } &&
            string.count { it in vowels } >= 3 &&
            couples.any { it in string }
    }

    override fun Sequence<String>.doPart2(): Int = count { string ->
        string.containsNonOverlappedCouple()
            && string.windowed(3, 1).any { it.first() == it.last() }
    }

    private fun String.containsNonOverlappedCouple(): Boolean {
        for (i in 0 until length - 2) {
            if (substring(i..i + 1) in substring(i + 2 until length)) {
                return true
            }
        }

        return false
    }

}

