package y2023

import java.io.File

fun calibrations(file: File): List<String> = file.readLines()

fun calibrationsSum(calibrations: List<String>, digitsBySymbol: Map<String, Int>): Int =
    calibrations.sumOf { calibrationValue(it, digitsBySymbol) }

fun calibrationValue(calibration: String, digitsBySymbol: Map<String, Int>): Int {
    val symbolSizes = digitsBySymbol.keys.map(String::length).toSortedSet()
    val digits = mutableListOf<Int>()
    for (i in calibration.indices) {
        for (s in symbolSizes) {
            if (i + s <= calibration.length) {
                val symbol = calibration.substring(i, i + s)
                val digit = digitsBySymbol[symbol]
                if (digit != null) {
                    digits.add(digit)
                }
            }
        }
    }
    val calibrationValue = digits.first() * 10 + digits.last()
    return calibrationValue
}
