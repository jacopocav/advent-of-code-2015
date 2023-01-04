package ceejay.advent

import ceejay.advent.util.isInteger

class LogicCircuit private constructor(private val wireMap: Map<String, String>) {
    private constructor(lines: Sequence<String>) : this(lines.map { it.split("->") }
        .associate { (expr, name) -> name.trim() to expr.trim() })

    private val cache = mutableMapOf<String, Int>()

    fun valueOfWire(name: String): Int {
        require(name in wireMap) { "invalid name: $name" }

        if (name in cache) {
            return cache[name]!!
        }

        val expr = wireMap[name]!!
        val tokens = expr.split(" ")

        val value = when (tokens.size) {
            1 -> valueOf(tokens.single())
            2 -> {
                assert(tokens.first() == "NOT") { "invalid unary operator: ${tokens.first()}" }
                valueOf(tokens.last()).inv()
            }

            3 -> valueOfBinary(left = tokens.first(), operand = tokens[1], right = tokens.last())

            else -> error("could not parse expression: $expr -> $name")
        }

        cache[name] = value

        return value
    }

    fun withWire(name: String, value: String): LogicCircuit {
        val newWireMap = (wireMap - name) + (name to value)
        return LogicCircuit(newWireMap)
    }

    private fun valueOf(text: String): Int =
        if (text.isInteger()) text.toInt() else valueOfWire(text)

    private fun valueOfBinary(left: String, operand: String, right: String): Int {
        val lValue = valueOf(left)
        val rValue = valueOf(right)

        return when (operand) {
            "AND" -> lValue and rValue
            "OR" -> lValue or rValue
            "RSHIFT" -> lValue shr rValue
            "LSHIFT" -> lValue shl rValue
            else -> error("unexpected operand: $operand")
        }
    }

    companion object {
        fun Sequence<String>.logicCircuit() = LogicCircuit(this)
    }
}