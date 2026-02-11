package y2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day01 {

    private val input = File("../inputs/202101.txt")

    private val example = """
        199
        200
        208
        210
        200
        207
        240
        269
        260
        263
    """.trimIndent()

    @Test
    fun example_part1() {
        val lines = example.lineSequence()
        val depths = depths(lines)
        val depthIncrements = depthIncrements(depths)
        assertEquals(7, depthIncrements)
    }

    @Test
    fun example_part2() {
        val lines = example.lineSequence()
        val depths = depths(lines)
        val depthIncrements = depthIncrements(depths, 3)
        assertEquals(5, depthIncrements)
    }

    @Test
    fun part1() = input.useLines { lines ->
        val depths = depths(lines)
        val depthIncrements = depthIncrements(depths)
        assertEquals(1655, depthIncrements)
    }

    @Test
    fun part2() = input.useLines { lines ->
        val depths = depths(lines)
        val depthIncrements = depthIncrements(depths, 3)
        assertEquals(1683, depthIncrements)
    }

}
