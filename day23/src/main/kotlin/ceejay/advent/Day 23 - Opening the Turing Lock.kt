package ceejay.advent

import ceejay.advent.Register.A
import ceejay.advent.Register.B
import ceejay.advent.util.Day
import ceejay.advent.util.illegal

object `Day 23 - Opening the Turing Lock` : Day<UInt, UInt>() {
    override val number = 23

    override fun Sequence<String>.doPart1(): UInt = with(Computer(debugEnabled)) {
        val instructions = parse()
        run(instructions)
        this[B]
    }

    override fun Sequence<String>.doPart2(): UInt = with(Computer(debugEnabled)) {
        val instructions = parse()
        this[A] = 1u
        run(instructions)
        this[B]
    }

    private fun Sequence<String>.parse(): List<Instruction> = mapIndexed { i, text ->
        val tokens = text.split(", ", " ")
        when (tokens.first()) {
            "hlf" -> Half(i, tokens[1].toRegister())
            "tpl" -> Triple(i, tokens[1].toRegister())
            "inc" -> Increment(i, tokens[1].toRegister())
            "jmp" -> Jump(i, tokens[1].toInt())
            "jie" -> JumpIfEven(i, tokens[1].toRegister(), tokens[2].toInt())
            "jio" -> JumpIfOne(i, tokens[1].toRegister(), tokens[2].toInt())
            else -> illegal("unsupported instruction: $text")
        }
    }.toList()

    private fun String.toRegister(): Register = Register.valueOf(uppercase())
}

