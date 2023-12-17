package y2023

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day06 {

    private val input = File("src/test/resources/y2023/Day06.txt")

    @Test
    fun part1() {
        val races = races(input)
        val multipliedWinningWays = races
            .map(::winningWays)
            .reduce { acc, w -> acc * w }
        assertEquals(114400, multipliedWinningWays)
    }

    @Test
    fun part2() {
        val race = race(input)
        val winningWays = winningWays(race)
        assertEquals(21039729, winningWays)
    }

}
