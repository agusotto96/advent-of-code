package y2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day01 {

    private val input = File("../inputs/202101.txt")

    @Test
    fun part1() {
        val depths = depths(input)
        assertEquals(1655, depthIncrease(depths))
    }

    @Test
    fun part2() {
        val depths = depths(input, 3)
        assertEquals(1683, depthIncrease(depths))
    }

}
