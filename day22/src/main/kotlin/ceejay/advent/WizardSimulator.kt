package ceejay.advent

import ceejay.advent.Spell.*
import ceejay.advent.util.*
import ceejay.advent.util.Debuggable.Companion.debug
import java.util.*
import kotlin.math.max

class WizardSimulator(
    private val wizardHealth: Int,
    private val wizardMana: Int,
    private val bossHealth: Int,
    private val bossDamage: Int,
    private val hardMode: Boolean = false,
) : Debuggable {
    override var debugEnabled: Boolean = false
    fun findCheapestManaWin(): Int? {
        val queue = priorityQueueComparing<State, _> { it.spentMana }
        queue += State(wizardHealth, wizardMana, 0, bossHealth, enumMapOfAll { 0 }, emptyList())

        while (queue.isNotEmpty()) {
            val state = queue.remove()

            if (state.bossHealth <= 0) {
                debug {
                    "Spells: " + state.castSpells.joinToString(" -> ") { spell ->
                        spell?.let { "$it (${it.manaCost})" } ?: "<nothing>"
                    }
                }
                return state.spentMana
            }

            queue += state.neighbors()
        }

        return null
    }

    private fun State.neighbors(): List<State> {
        val afterTurnStart = startWizardTurn() ?: return emptyList()

        return afterTurnStart.run {
            if (remainingMana < Spell.minCost) {
                listOf(copy(castSpells = castSpells + null))
                    .mapNotNull { it.playBossTurn() }
            } else {
                Spell.all
                    .filter { spellTimers[it] == 0 && remainingMana >= it.manaCost }
                    .mapNotNull { spell ->
                        val newMana = remainingMana - spell.manaCost
                        var newHealth = health
                        var newBossHealth = bossHealth
                        val newActiveSpells = spellTimers.copy()

                        when (spell) {
                            MAGIC_MISSILE -> newBossHealth -= 4
                            DRAIN -> {
                                newBossHealth -= 2
                                newHealth += 2
                            }

                            else -> newActiveSpells[spell] = spell.duration
                        }

                        copy(
                            health = newHealth,
                            remainingMana = newMana,
                            spentMana = spentMana + spell.manaCost,
                            bossHealth = newBossHealth,
                            spellTimers = newActiveSpells,
                            castSpells = castSpells + spell
                        ).playBossTurn()
                    }
            }
        }
    }

    private fun State.startWizardTurn(): State? {
        val newHealth = if (hardMode) health - 1 else health

        if (newHealth <= 0) {
            return null
        }

        val (newMana, newBossHealth, _, newTimers) = triggerSpells()

        return copy(
            health = newHealth,
            remainingMana = newMana,
            bossHealth = newBossHealth,
            spellTimers = newTimers,
            prev = this,
        )
    }

    private fun State.playBossTurn(): State? {
        val (newMana, newBossHealth, newArmor, newTimers) = triggerSpells()

        val newHealth =
            if (newBossHealth > 0) health - max(1, bossDamage - newArmor)
            else health

        if (newHealth <= 0) {
            return null
        }

        return copy(
            health = newHealth,
            remainingMana = newMana,
            bossHealth = newBossHealth,
            spellTimers = newTimers,
            prev = this,
        )
    }

    private fun State.triggerSpells(): TurnContext {
        var newMana = remainingMana
        var newBossHealth = bossHealth
        var newArmor = armor

        val newTimers = spellTimers
            .mapValuesTo(enumMapOf()) { (spell, timer) ->
                if (timer <= 0) 0
                else {
                    when (spell!!) {
                        POISON -> newBossHealth -= 3
                        RECHARGE -> newMana += 101
                        SHIELD -> newArmor = if (timer - 1 == 0) 0 else 7
                        MAGIC_MISSILE, DRAIN -> error("instant spell $spell found with non-zero duration")
                    }
                    timer - 1
                }
            }

        return TurnContext(newMana, newBossHealth, newArmor, newTimers)
    }

    private data class TurnContext(
        val mana: Int,
        val bossHealth: Int,
        val armor: Int,
        val timers: EnumMap<Spell, Int>,
    )

    private data class State(
        val health: Int,
        val remainingMana: Int,
        val spentMana: Int,
        val bossHealth: Int,
        val spellTimers: EnumMap<Spell, Int>,
        val castSpells: List<Spell?>,
        var prev: State? = null,
    ) {
        val armor = if (spellTimers[SHIELD]!! > 0) 7 else 0
    }
}