package y2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day07 {

    private val input = File("src/test/resources/y2021/Day07.txt")

    @Test
    fun part1() {
        val positions = positions(input)
        assertEquals(351901, bestPositionFuelCost(positions, ::constantFuelCost))
    }

    @Test
    fun part2() {
        val positions = positions(input)
        assertEquals(101079875, bestPositionFuelCost(positions, ::incrementalFuelCost))
    }

}