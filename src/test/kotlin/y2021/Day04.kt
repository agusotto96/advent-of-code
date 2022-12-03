package y2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day04 {

    private val input = File("src/test/resources/y2021/Day04.txt")

    @Test
    fun part1() {
        val bingo = Bingo(input, 5)
        assertEquals(4512, findFirstWinnerScore(bingo))
    }

    @Test
    fun part2() {
        val bingo = Bingo(input, 5)
        assertEquals(1924, findLastWinnerScore(bingo))
    }

}