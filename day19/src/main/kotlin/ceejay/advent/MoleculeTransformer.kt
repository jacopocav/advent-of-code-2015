package ceejay.advent

class MoleculeTransformer(transformations: List<Pair<String, String>>) {
    init {
        require(transformations.all { (from, to) -> from.length <= to.length })
    }

    private val transformations =
        transformations.sortedByDescending { (from, to) -> to.length - from.length }

    fun findShortestTransformationChain(from: String, to: String): Int {
        validate(from, to)

        var current = to
        var steps = 0

        // bottom-up greedy search
        // greedy choice: always select the first transformation that shortens the molecule the most
        while (current != from) {
            for ((source, target) in transformations) {
                if (target !in current || (source == from && target != current)) {
                    continue
                }

                val next = current.replaceFirst(target, source)

                steps++
                current = next
            }
        }

        check(current == from) { "No path from $from to $to found" }
        return steps
    }

    private fun validate(from: String, to: String) {
        require(from.length <= to.length) {
            "cannot transform a longer molecule into a shorter one"
        }
        require(transformations.any { (source, _) -> source in from }) {
            "no transformation found for '$from'"
        }
        require(transformations.none { (_, target) -> from in target }) {
            "detected transformation loop for 'from'"
        }
    }
}