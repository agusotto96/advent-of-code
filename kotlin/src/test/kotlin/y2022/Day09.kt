package y2022

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class Day09 {

    private val input = File("../inputs/202209.txt")

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
