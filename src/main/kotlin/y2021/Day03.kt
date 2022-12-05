package y2021

import java.io.File

fun lifeSupportRating(report: List<String>): Int {
    val oxygenGeneratorRating = oxygenGeneratorRating(report)
    val co2ScrubberRating = co2ScrubberRating(report)
    return Integer.parseInt(oxygenGeneratorRating, 2) * Integer.parseInt(co2ScrubberRating, 2)
}

fun oxygenGeneratorRating(report: List<String>): String {
    val length = report.first().length
    var remaining = report
    for (index in 0 until length) {
        val startingWith0 = mutableListOf<String>()
        val startingWith1 = mutableListOf<String>()
        for (number in remaining) (if (number[index] == '0') startingWith0 else startingWith1).add(number)
        remaining = if (startingWith0.size > startingWith1.size) startingWith0 else startingWith1
        if (remaining.size == 1) return remaining.single()
    }
    throw Exception()
}

fun co2ScrubberRating(report: List<String>): String {
    val length = report.first().length
    var remaining = report
    for (index in 0 until length) {
        val startingWith0 = mutableListOf<String>()
        val startingWith1 = mutableListOf<String>()
        for (number in remaining) (if (number[index] == '0') startingWith0 else startingWith1).add(number)
        remaining = if (startingWith0.size <= startingWith1.size) startingWith0 else startingWith1
        if (remaining.size == 1) return remaining.single()
    }
    throw Exception()
}

fun report(file: File): List<String> =
    file.readLines()

fun powerConsumption(report: List<String>): Int {
    val gammaRate = gammaRate(report)
    val epsilonRate = epsilonRate(report)
    return Integer.parseInt(gammaRate, 2) * Integer.parseInt(epsilonRate, 2)
}

fun gammaRate(report: List<String>): String {
    val length = report.first().length
    val gammaRate = CharArray(length)
    for (index in 0 until length) {
        var bitCount = 0
        for (number in report) {
            if (number[index] == '1') {
                bitCount++
            }
        }
        gammaRate[index] = if (bitCount > report.count() / 2) '1' else '0'
    }
    return gammaRate.concatToString()
}

fun epsilonRate(report: List<String>): String =
    gammaRate(report)
        .map { if (it == '0') '1' else '0' }
        .joinToString("")