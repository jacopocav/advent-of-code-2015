package ceejay.advent

import ceejay.advent.LogicCircuit.Companion.logicCircuit
import ceejay.advent.util.Day

object `Day 7 - Some Assembly Required` : Day<UShort, UShort>() {
    override val number = 7

    override fun Sequence<String>.doPart1(): UShort = with(logicCircuit()) {
        valueOfWire("a").toUShort()
    }

    override fun Sequence<String>.doPart2(): UShort = with(logicCircuit()) {
        val value = valueOfWire("a")

        withWire("b", value.toString())
            .valueOfWire("a")
            .toUShort()
    }
}

