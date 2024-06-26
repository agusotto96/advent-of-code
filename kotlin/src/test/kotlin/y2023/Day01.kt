package y2023

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day01 {

    private val input = File("../inputs/202301.txt")

    @Test
    fun part1() {
        val digitsBySymbol = mapOf(
            "1" to 1,
            "2" to 2,
            "3" to 3,
            "4" to 4,
            "5" to 5,
            "6" to 6,
            "7" to 7,
            "8" to 8,
            "9" to 9,
        )
        val calibrations = calibrations(input)
        val calibrationsSum = calibrations.sumOf { calibrationValue(it, digitsBySymbol) }
        assertEquals(54331, calibrationsSum)
    }

    @Test
    fun part2() {
        val digitsBySymbol = mapOf(
            "1" to 1,
            "2" to 2,
            "3" to 3,
            "4" to 4,
            "5" to 5,
            "6" to 6,
            "7" to 7,
            "8" to 8,
            "9" to 9,
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9,
        )
        val calibrations = calibrations(input)
        val calibrationsSum = calibrations.sumOf { calibrationValue(it, digitsBySymbol) }
        assertEquals(54518, calibrationsSum)
    }

}
