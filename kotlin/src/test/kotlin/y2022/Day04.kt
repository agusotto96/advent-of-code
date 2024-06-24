package y2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day04 {

    private val input = File("../inputs/202204.txt")

    @Test
    fun part1() {
        val elfSectionsPairs = elfSectionsPairs(input)
        assertEquals(424, fullOverlapCount(elfSectionsPairs))
    }

    @Test
    fun part2() {
        val elfSectionsPairs = elfSectionsPairs(input)
        assertEquals(804, overlapCount(elfSectionsPairs))
    }

}
