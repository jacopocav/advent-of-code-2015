package ceejay.advent

import ceejay.advent.util.Day
import ceejay.advent.util.illegal

object `Day 22 - Wizard Simulator 20XX` : Day<Int?, Int?>(ignoreBlankLines = false) {
    override val number = 22
    private val defaultWizard = 50 to 500

    override fun Sequence<String>.doPart1(): Int? {
        val (wizardHealth, wizardMana, bossHealth, bossDamage) = parse()

        return WizardSimulator(wizardHealth, wizardMana, bossHealth, bossDamage)
            .also { it.debugEnabled = debugEnabled }
            .findCheapestManaWin()
    }

    override fun Sequence<String>.doPart2(): Int? {
        val (wizardHealth, wizardMana, bossHealth, bossDamage) = parse()

        return WizardSimulator(wizardHealth, wizardMana, bossHealth, bossDamage, hardMode = true)
            .also { it.debugEnabled = debugEnabled }
            .findCheapestManaWin()
    }

    private fun Sequence<String>.parse(): Duel {
        val lines = toList()
        val (bossHealth, bossDamage) = lines.takeWhile { it.isNotBlank() }
            .map { it.split(":") }
            .associate { (stat, value) -> stat.trim() to value.trim().toInt() }
            .let {
                (it["Hit Points"] ?: illegal("'Hit Points' stat not found for boss")) to
                    (it["Damage"] ?: illegal("'Damage' stat not found for boss"))
            }

        val (wizardHealth, wizardMana) = if (lines.any { it.isBlank() }) {
            lines.takeLastWhile { it.isNotBlank() }
                .map { it.split(":") }
                .associate { (stat, value) -> stat.trim() to value.trim().toInt() }
                .let {
                    (it["Hit Points"] ?: illegal("'Hit Points' stat not found for wizard")) to
                        (it["Mana"] ?: illegal("'Mana' stat not found for wizard"))
                }
        } else {
            defaultWizard
        }

        return Duel(
            wizardHealth = wizardHealth,
            wizardMana = wizardMana,
            bossHealth = bossHealth,
            bossDamage = bossDamage,
        )
    }

    private data class Duel(
        val wizardHealth: Int,
        val wizardMana: Int,
        val bossHealth: Int,
        val bossDamage: Int,
    )
}

