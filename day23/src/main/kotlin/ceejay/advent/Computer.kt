package ceejay.advent

import ceejay.advent.util.Debuggable
import ceejay.advent.util.Debuggable.Companion.debug
import ceejay.advent.util.enumMapOfAll

class Computer(override var debugEnabled: Boolean = false) : Debuggable {
    private val registers: MutableMap<Register, UInt> = enumMapOfAll { 0u }

    operator fun set(register: Register, value: UInt) {
        registers[register] = value
    }

    operator fun get(register: Register) = registers[register]!!

    fun run(instructions: List<Instruction>) {
        var pc = 0
        debug { " PC | NEXT INSTR | REGISTERS" }
        debug { "----+------------+----------" }
        while (pc in instructions.indices) {
            val instruction = instructions[pc]
            debug {
                pc.toString().padStart(3) + " | " +
                    instruction.toString().padEnd(10) + " | " +
                    registers
            }
            instruction(this)
            pc = instruction.nextInstruction(this)
        }
    }

    companion object {
        inline fun Computer.update(register: Register, mapping: (UInt) -> UInt) {
            this[register] = mapping(this[register])
        }
    }
}