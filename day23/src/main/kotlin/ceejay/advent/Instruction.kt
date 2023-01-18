package ceejay.advent

import ceejay.advent.Computer.Companion.update
import ceejay.advent.util.isEven

sealed interface Instruction {
    val line: Int

    operator fun invoke(computer: Computer) {
        // does nothing by default
    }

    fun nextInstruction(computer: Computer): Int = line + 1
}

data class Half(override val line: Int, val register: Register) : Instruction {
    override fun invoke(computer: Computer) {
        computer.update(register) { it / 2u }
    }

    override fun toString(): String = "hlf ${register.name.lowercase()}"
}

data class Triple(override val line: Int, val register: Register) : Instruction {
    override fun invoke(computer: Computer) {
        computer.update(register) { it * 3u }
    }

    override fun toString(): String = "tpl ${register.name.lowercase()}"
}

data class Increment(override val line: Int, val register: Register) : Instruction {
    override fun invoke(computer: Computer) {
        computer.update(register) { it + 1u }
    }

    override fun toString(): String = "inc ${register.name.lowercase()}"
}

data class Jump(override val line: Int, val offset: Int) : Instruction {
    override fun nextInstruction(computer: Computer): Int = line + offset

    override fun toString(): String = "jmp ${offset.withSign()}"
}

data class JumpIfEven(override val line: Int, val register: Register, val offset: Int) :
    Instruction {
    override fun nextInstruction(computer: Computer): Int = line +
        if (computer[register].isEven()) offset
        else 1

    override fun toString(): String = "jie ${register.name.lowercase()}, ${offset.withSign()}"
}

data class JumpIfOne(override val line: Int, val register: Register, val offset: Int) :
    Instruction {
    override fun nextInstruction(computer: Computer): Int = line +
        if (computer[register] == 1u) offset
        else 1

    override fun toString(): String = "jio ${register.name.lowercase()}, ${offset.withSign()}"
}

private fun Int.withSign() = if (this >= 0) "+$this" else toString()