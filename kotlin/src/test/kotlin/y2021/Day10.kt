package y2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day10 {

    private val input = File("src/test/resources/y2021/Day10.txt")

    @Test
    fun part1() {
        assertEquals(415953, fileCorruptedScore(input))
    }

    @Test
    fun part2() {
        assertEquals(2292863731, fileUnclosedScore(input))
    }

}
