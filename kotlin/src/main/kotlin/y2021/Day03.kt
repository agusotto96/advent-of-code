package y2021

import java.io.File


data class Report(
    val numbers: List<UInt>,
    val width: Int,
)

data class Rates(
    val gamma: UInt = 0u,
    val epsilon: UInt = 0u,
)

fun report(file: File): Report {
    val lines = file.readLines()
    return Report(
        numbers = lines.map { it.toUInt(2) },
        width = lines.first().count(),
    )
}

fun powerConsumption(report: Report): UInt {
    val (gamma, epsilon) = rates(report)
    return gamma * epsilon
}

fun rates(report: Report): Rates =
    (0 until report.width)
        .asSequence()
        .map { 1u shl it }
        .fold(Rates()) { rates, mask ->
            if (report.numbers.count { it and mask > 0u } > report.numbers.count() / 2) {
                rates.copy(gamma = rates.gamma or mask)
            } else {
                rates.copy(epsilon = rates.epsilon or mask)
            }
        }

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
