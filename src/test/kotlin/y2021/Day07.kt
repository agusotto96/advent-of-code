package y2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day07 {

    private val input = File("src/test/resources/y2021/Day07.txt")

    @Test
    fun part1() {
        val positions = positions(input)
        val meanPosition = meanPosition(positions)
        val fuelCost = positions.sumOf { constantFuelCost(it, meanPosition) }
        assertEquals(351901, fuelCost)
    }

    @Test
    fun part2() {
        val positions = positions(input)
        val (min, max) = positionRange(positions)!!
        val fuelCost = (min..max).minOf { p -> positions.sumOf { incrementalFuelCost(it, p) } }
        assertEquals(101079875, fuelCost)
    }

}
