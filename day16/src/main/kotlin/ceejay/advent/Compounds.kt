package ceejay.advent

import java.util.*

data class Compounds(
    val children: Int? = null,
    val cats: Int? = null,
    val samoyeds: Int? = null,
    val pomeranians: Int? = null,
    val akitas: Int? = null,
    val vizslas: Int? = null,
    val goldfish: Int? = null,
    val trees: Int? = null,
    val cars: Int? = null,
    val perfumes: Int? = null,
) {

    override fun equals(other: Any?): Boolean = other is Compounds
        && equalOrAnyNull(children, other.children)
        && equalOrAnyNull(cats, other.cats)
        && equalOrAnyNull(samoyeds, other.samoyeds)
        && equalOrAnyNull(pomeranians, other.pomeranians)
        && equalOrAnyNull(akitas, other.akitas)
        && equalOrAnyNull(vizslas, other.vizslas)
        && equalOrAnyNull(goldfish, other.goldfish)
        && equalOrAnyNull(trees, other.trees)
        && equalOrAnyNull(cars, other.cars)
        && equalOrAnyNull(perfumes, other.perfumes)

    override fun hashCode(): Int = Objects.hash(
        children,
        cats,
        samoyeds,
        pomeranians,
        akitas,
        vizslas,
        goldfish,
        trees,
        cars,
        perfumes
    )

    companion object {
        private fun equalOrAnyNull(value: Any?, other: Any?): Boolean =
            value == null || other == null || value == other

        fun fromMap(map: Map<String, Int>): Compounds = Compounds(
            children = map["children"],
            cats = map["cats"],
            samoyeds = map["samoyeds"],
            pomeranians = map["pomeranians"],
            akitas = map["akitas"],
            vizslas = map["vizslas"],
            goldfish = map["goldfish"],
            trees = map["trees"],
            cars = map["cars"],
            perfumes = map["perfumes"],
        )
    }
}