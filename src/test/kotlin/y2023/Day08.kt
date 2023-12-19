package y2023

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day08 {

    private val input = File("src/test/resources/y2023/Day08.txt")

    @Test
    fun part1() {
        val maps = Maps(input)
        val steps = stepsToEnd(maps)
        assertEquals(19637, steps)
    }

}
