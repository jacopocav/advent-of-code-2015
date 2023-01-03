package ceejay.advent.util

interface Debuggable {
    val debugEnabled: Boolean

    companion object {
        inline fun Debuggable.debug(lazyMessage: () -> String = { "" }) {
            if (debugEnabled) {
                println(lazyMessage())
            }
        }
    }
}
