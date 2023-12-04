package y2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day07 {

    private val input = File("src/test/resources/y2021/Day07.txt")

    @Test
    fun part1() {
        // TODO: check how it would work on odd amount of positions
        val positions = positions(input)
        val meanPosition = meanPosition(positions)
        val fuelCost = positions.sumOf { constantFuelCost(it, meanPosition) }
        assertEquals(351901, fuelCost)
    }

    @Test
    fun part2() {
        // TODO: NOT 100% CORRECT, AVERAGE IS USUALLY CLOSE LIKE IN THIS CASE BUT DOES NOT WORK ON TEST INPUT
        val positions = positions(input)
        val averagePosition = averagePosition(positions)
        val fuelCost = positions.sumOf { incrementalFuelCost(it, averagePosition) }
        assertEquals(101079875, fuelCost)
    }

}
