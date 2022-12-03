package y2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day08 {

    private val input = File("src/test/resources/y2021/Day08.txt")

    @Test
    fun part1() {
        val displays = displays(input)
        assertEquals(26, uniqueSegmentNumberDigits(displays))
    }

    @Test
    fun part2() {
        val displays = displays(input)
        assertEquals(61229, displaysOutputSum(displays))
    }

}