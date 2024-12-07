package y2019

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day05 {

    private val input = File("../inputs/201905.txt")

    @Test
    fun part1() {
        val intcodes = intcodes(input)
        assertEquals(10987514, run(intcodes.toMutableList(), null, null, 1))
    }

    @Test
    fun part2() {
        val intcodes = intcodes(input)
        assertEquals(14195011, run(intcodes.toMutableList(), null, null, 5))
    }

}
