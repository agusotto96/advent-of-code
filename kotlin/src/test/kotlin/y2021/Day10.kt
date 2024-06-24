package y2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day10 {

    private val input = File("../inputs/202110.txt")

    @Test
    fun part1() {
        assertEquals(415953, fileCorruptedScore(input))
    }

    @Test
    fun part2() {
        assertEquals(2292863731, fileUnclosedScore(input))
    }

}
