package ceejay.advent

import ceejay.advent.util.Day
import ceejay.advent.util.isEven
import java.security.MessageDigest

object `Day 4 - The Ideal Stocking Stuffer` : Day<Int, Int>() {
    override val number = 4

    private const val zero: Byte = 0
    private const val sixteen: UByte = 16u

    override fun Sequence<String>.doPart1(): Int = run(5)

    override fun Sequence<String>.doPart2(): Int = run(6)

    private fun Sequence<String>.run(zeros: Int): Int {
        val input = single()
        val md5 = MessageDigest.getInstance("MD5")
        return (1..Int.MAX_VALUE)
            .first { (input + it).digest(md5).startsWithHexZeros(zeros) }
    }

    private fun String.digest(algorithm: MessageDigest) = algorithm.digest(encodeToByteArray())

    private fun ByteArray.startsWithHexZeros(num: Int): Boolean {
        val half = num / 2
        for (i in 0 until half) {
            if (this[i] != zero) return false
        }
        return num.isEven() || this[half].toUByte() < sixteen
    }
}

