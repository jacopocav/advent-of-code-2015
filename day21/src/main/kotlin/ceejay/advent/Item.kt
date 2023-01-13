package ceejay.advent

data class Item(
    val name: String,
    val type: Type,
    val cost: Int,
    val attack: Int = 0,
    val defense: Int = 0,
) {
    enum class Type(val max: Int) {
        WEAPON(1), ARMOR(1), RING(2)
    }

    companion object {
        private val dummies = mutableMapOf<Type, Item>()
        fun dummy(type: Type): Item =
            dummies.getOrPut(type) { Item("dummy ${type.name.lowercase()}", type, 0) }
    }
}