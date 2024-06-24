package y2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day06 {

    private val input = File("../inputs/202106.txt")

    @Test
    fun part1() {
        val population = population(input)
        assertEquals(391888, simulateGrowth(population, 80))
    }

    @Test
    fun part2() {
        val population = population(input)
        assertEquals(1754597645339, simulateGrowth(population, 256))
    }

}
