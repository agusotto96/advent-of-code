package y2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day01 {

    private val input = File("../inputs/202201.txt")

    @Test
    fun part1() {
        val elvesCalories = elvesCalories(input)
        assertEquals(67658, maxElfCaloriesSum(elvesCalories))
    }

    @Test
    fun part2() {
        val elvesCalories = elvesCalories(input)
        assertEquals(200158, maxElfCaloriesSum(elvesCalories, 3))
    }

}
