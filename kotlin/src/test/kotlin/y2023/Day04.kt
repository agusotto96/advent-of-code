package y2023

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day04 {

    private val input = File("../inputs/202304.txt")

    @Test
    fun part1() {
        val scratchcards = scratchcards(input)
        val totalPoints = scratchcards.map(::points).sum()
        assertEquals(21568, totalPoints)
    }

    @Test
    fun part2() {
        val scratchcards = scratchcards(input)
        val totalScratchcards = totalScratchcards(scratchcards)
        assertEquals(11827296, totalScratchcards)
    }

}
