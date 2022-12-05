package y2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day05 {

    private val input = File("src/test/resources/y2021/Day05.txt")

    @Test
    fun part1() {
        val lines = lines(input)
        assertEquals(6548, overlappingPoints(lines))
    }

    @Test
    fun part2() {
        val lines = lines(input)
        assertEquals(19663, overlappingPoints(lines, diagonals = true))
    }

}