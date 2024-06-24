package y2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day09 {

    private val input = File("../inputs/202109.txt")

    @Test
    fun part1() {
        val heightmap = heightmap(input)
        assertEquals(594, lowestHeightsRiskLevel(heightmap))
    }

    @Test
    fun part2() {
        val heightmap = heightmap(input)
        assertEquals(858494, multiplyBiggestBasinSizes(heightmap, 3))
    }

}
