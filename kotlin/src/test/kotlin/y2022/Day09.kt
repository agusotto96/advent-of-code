package y2022

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class Day09 {

    private val input = File("src/test/resources/y2022/Day09.txt")

    @Test
    fun part1() {
        val motions = motions(input)
        assertEquals(6642, countTailPositions(motions, 2))
    }

    @Test
    fun part2() {
        val motions = motions(input)
        assertEquals(2765, countTailPositions(motions, 10))
    }

}
