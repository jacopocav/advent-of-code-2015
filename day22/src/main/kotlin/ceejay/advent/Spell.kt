package ceejay.advent

enum class Spell(val spellName: String, val manaCost: Int, val duration: Int) {
    MAGIC_MISSILE("Magic Missile", 53, 0),
    DRAIN("Drain", 73, 0),
    SHIELD("Shield", 113, 6),
    POISON("Poison", 173, 6),
    RECHARGE("Recharge", 229, 5);

    companion object {
        val all = values().toSet()
        val minCost = all.minOf { it.manaCost }
    }
}