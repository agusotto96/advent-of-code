package y2023

import java.io.File
import kotlin.math.pow

data class Scratchcard(
    val id: Int,
    val winningNumbers: List<Int>,
    val ownedNumbers: List<Int>,
)

fun scratchcards(file: File): List<Scratchcard> =
    file.readLines()
        .map { it.split(" | ") }
        .map {
            val (id, winningNumbers) = it.first().split(": ")
            val ownedNumbers = it.last()
            Scratchcard(
                id = id.filter(Char::isDigit).toInt(),
                winningNumbers = winningNumbers.split(" ").filter(String::isNotBlank).map(String::toInt),
                ownedNumbers = ownedNumbers.split(" ").filter(String::isNotBlank).map(String::toInt),
            )
        }

fun points(scratchcard: Scratchcard): Int {
    val (_, winningNumbers, ownedNumbers) = scratchcard
    val matchingNumbers = ownedNumbers.count(winningNumbers::contains)
    return 2.0.pow(matchingNumbers - 1).toInt()
}

fun totalScratchcards(scratchcards: List<Scratchcard>): Int {
    val copyCounts = mutableMapOf<Int, Int>()
    for (scratchcard in scratchcards) {
        val (id, winningNumbers, ownedNumbers) = scratchcard
        val matchingNumbers = ownedNumbers.count(winningNumbers::contains)
        val copies = (copyCounts[id] ?: 0) + 1
        for (matchingNumber in 1..matchingNumbers) {
            copyCounts[id + matchingNumber] = ((copyCounts[id + matchingNumber] ?: 0) + copies)
        }
    }
    return copyCounts.values.sum() + scratchcards.size
}
