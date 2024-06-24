package y2023

import java.io.File

fun histories(file: File): List<List<Long>> =
    file.readLines().map { it.split(" ").map(String::toLong) }

fun historiesTotalSum(histories: List<List<Long>>): Long {
    val diffsSum = histories.flatMap(::historyDiffs).sumOf { it.last() }
    val historiesSum = histories.sumOf { it.last() }
    return diffsSum + historiesSum
}

fun historyDiffs(history: List<Long>): List<List<Long>> {
    val diff = history.windowed(2).map { (a, b) -> b - a }
    return if (diff.all { it == diff.first() }) {
        listOf(diff)
    } else {
        historyDiffs(diff) + listOf(diff)
    }
}
