package y2023

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day02 {

    private val input = File("../inputs/202302.txt")

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
        val minimumBagPowerSum = games.sumOf(::minimumBagPower)
        assertEquals(74229, minimumBagPowerSum)
    }

}
