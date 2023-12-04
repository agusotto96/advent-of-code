package y2023

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day02 {

    private val input = File("src/test/resources/y2023/Day02.txt")

    @Test
    fun part1() {
        val bag = mapOf(
            Color.Red to 12,
            Color.Green to 13,
            Color.Blue to 14,
        )
        val games = games(input)
        val possibleGamesIdSum = games.filter { isPossibleGame(it, bag) }.sumOf(Game::id)
        assertEquals(2256, possibleGamesIdSum)
    }

    @Test
    fun part2() {
        val games = games(input)
        val minimumBagPowerSum = games.map(::minimumBagPower).sum()
        assertEquals(74229, minimumBagPowerSum)
    }

}
