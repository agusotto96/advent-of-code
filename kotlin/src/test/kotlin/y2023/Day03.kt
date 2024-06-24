package y2023

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day03 {

    private val input = File("../inputs/202303.txt")

    @Test
    fun part1() {
        val engineSchematic = EngineSchematic(input)
        val partNumbersSum = partNumbers(engineSchematic).sum()
        assertEquals(532445, partNumbersSum)
    }

    @Test
    fun part2() {
        val engineSchematic = EngineSchematic(input)
        val gearRatiosSum = gearRatios(engineSchematic).sum()
        assertEquals(79842967, gearRatiosSum)
    }

}
