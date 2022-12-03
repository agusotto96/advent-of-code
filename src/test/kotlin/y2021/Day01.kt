package y2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day01 {

    private val input = File("src/test/resources/y2021/Day01.txt")

    @Test
    fun part1() {
        val depths = depths(input)
        assertEquals(7, depthIncrease(depths))
    }

    @Test
    fun part2() {
        val depths = depths(input, 3)
        assertEquals(5, depthIncrease(depths))
    }

}