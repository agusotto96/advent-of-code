package y2019

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day02 {

    private val input = File("../inputs/201902.txt")

    @Test
    fun part1() {
        val intcodes = intcodes(input)
        assertEquals(2842648, run(intcodes, 12, 2))
    }

    @Test
    fun part2() {
        val intcodes = intcodes(input)
        for (noun in 0..99) {
            for (verb in 0..99) {
                if (run(intcodes, noun, verb) == 19690720) {
                    assertEquals(9074, 100 * noun + verb)
                }
            }
        }
    }

}
