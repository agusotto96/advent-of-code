package y2023

import java.io.File

data class Hand(
    val cards: List<Char>,
    val bid: Int,
    val jokers: Boolean,
) : Comparable<Hand> {

    private val typeStrength = handTypeStrength(cards, jokers)
    private val cardStrengths = cards.map { cardStrength(it, jokers) }

    override fun compareTo(other: Hand): Int {
        if (this.typeStrength > other.typeStrength) {
            return 1
        }
        if (this.typeStrength < other.typeStrength) {
            return -1
        }
        for ((thisCardStrength, otherCardStrength) in this.cardStrengths.zip(other.cardStrengths)) {
            if (thisCardStrength > otherCardStrength) {
                return 1
            }
            if (thisCardStrength < otherCardStrength) {
                return -1
            }
        }
        return 0
    }

}

fun hands(file: File, jokers: Boolean = false): List<Hand> =
    file.readLines()
        .map { line -> line.split(" ") }
        .map { (cards, bid) -> Hand(cards.toList(), bid.let(String::toInt), jokers) }

fun handTypeStrength(cards: List<Char>, jokers: Boolean): Int {
    val counts = cards.groupingBy { it }.eachCount().values.toMutableList()
    if (jokers) {
        val jokersCount = cards.count { it == 'J' }
        if (jokersCount in 1..4) {
            counts.remove(jokersCount)
            val max = counts.maxOrNull()!!
            counts.remove(max)
            counts.add(jokersCount + max)
        }
    }
    return when (counts.sorted()) {
        listOf(5) -> 7
        listOf(1, 4) -> 6
        listOf(2, 3) -> 5
        listOf(1, 1, 3) -> 4
        listOf(1, 2, 2) -> 3
        listOf(1, 1, 1, 2) -> 2
        listOf(1, 1, 1, 1, 1) -> 1
        else -> 0
    }
}

fun cardStrength(card: Char, jokers: Boolean): Int =
    when (card) {
        'A' -> 13
        'K' -> 12
        'Q' -> 11
        'J' -> if (jokers) 0 else 10
        'T' -> 9
        '9' -> 8
        '8' -> 7
        '7' -> 6
        '6' -> 5
        '5' -> 4
        '4' -> 3
        '3' -> 2
        '2' -> 1
        else -> 0
    }