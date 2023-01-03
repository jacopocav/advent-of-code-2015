package ceejay.advent

import ceejay.advent.util.Day
import ceejay.advent.util.isEven
import java.security.MessageDigest

@OptIn(ExperimentalUnsignedTypes::class)
object `Day 4 - The Ideal Stocking Stuffer` : Day<Int, Int>() {
    override val number = 4

    private val zero: UByte = 0u
    private val sixteen: UByte = 16u

    private val md5: MessageDigest = MessageDigest.getInstance("MD5")

    override fun Sequence<String>.doPart1(): Int {
        val input = single()
        return generateSequence(1) { it + 1 }
            .first { n ->
                (input + n).md5().startsWithZeros(5)
            }
    }

    override fun Sequence<String>.doPart2(): Int {
        val input = single()
        return generateSequence(1) { it + 1 }
            .first { n ->
                (input + n).md5().startsWithZeros(6)
            }
    }

    private fun String.md5() = md5.digest(encodeToByteArray())

    private fun ByteArray.startsWithZeros(num: Int): Boolean {
        val half = num / 2
        return indexOfFirst { it.toUByte() != zero } >= half
            && (num.isEven() || this[half].toUByte() < sixteen)
    }
}

