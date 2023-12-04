package y2023

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day03 {

    private val input = File("src/test/resources/y2023/Day03.txt")

    @Test
    fun part1() {
        val engineSchematic = EngineSchematic(input)
        val partNumbersSum = partNumbersSum(engineSchematic)
        assertEquals(532445, partNumbersSum)
    }

    @Test
    fun part2() {
        val engineSchematic = EngineSchematic(input)
        val gearRatiosSum = gearRatiosSum(engineSchematic)
        assertEquals(79842967, gearRatiosSum)
    }

}
