package ceejay.advent

import ceejay.advent.util.Rectangle

sealed class Action(
    val rectangle: Rectangle,
    private val name: String,
) {
    override fun toString(): String = "$name ${rectangle.topLeft.x},${rectangle.topLeft.y} " +
        "through ${rectangle.bottomRight.x},${rectangle.bottomRight.y}"
}

class Toggle(rectangle: Rectangle) : Action(rectangle, "toggle")

class TurnOn(rectangle: Rectangle) : Action(rectangle, "turn on")

class TurnOff(rectangle: Rectangle) : Action(rectangle, "turn off")