package ceejay.advent.util

import java.io.FileNotFoundException

sealed interface Input {
    val fileName: String

    object Example : Input {
        override val fileName = "example.txt"
    }

    object Real : Input {
        override val fileName = "input.txt"
    }

    companion object {
        val newLine: String = System.lineSeparator()
    }
}

inline fun <T> Input.useLines(
    ignoreBlankLines: Boolean = true,
    block: (Sequence<String>) -> T,
) = withLines(ignoreBlankLines, block)


inline fun <T> Input.withLines(
    ignoreBlankLines: Boolean = true,
    block: Sequence<String>.() -> T,
): T {
    val reader = ClassLoader.getSystemResourceAsStream(fileName)
        ?.buffered()
        ?.reader()
        ?: throw FileNotFoundException(fileName)

    return reader.useLines { lines ->
        lines.run {
            if (ignoreBlankLines) filter { it.isNotBlank() }
            else this
        }.block()
    }
}

