package y2022

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class Day10 {

    private val input = File("src/test/resources/y2022/Day10.txt")

    @Test
    fun part1() {
        val instructions = instructions(input)
        assertEquals(14420, signalStrength(instructions))
    }

    @Test
    fun part2() {

    }

}
