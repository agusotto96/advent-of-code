package y2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day08 {

    private val input = File("../inputs/202108.txt")

    @Test
    fun part1() {
        val displays = displays(input)
        assertEquals(554, uniqueSegmentNumberDigits(displays))
    }

    @Test
    fun part2() {
        val displays = displays(input)
        assertEquals(990964, displaysOutputSum(displays))
    }

}
