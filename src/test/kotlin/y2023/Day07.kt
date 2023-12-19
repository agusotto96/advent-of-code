package y2023

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day07 {

    private val input = File("src/test/resources/y2023/Day07.txt")

    @Test
    fun part1() {
        val hands = hands(input)
        val totalWinnings = hands
            .sorted()
            .withIndex()
            .sumOf { (index, hand) -> (index + 1) * hand.bid }
        assertEquals(247823654, totalWinnings)
    }

}
