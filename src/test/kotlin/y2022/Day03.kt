package y2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day03 {

    private val input = File("src/test/resources/y2022/Day03.txt")

    @Test
    fun part1() {
        val rucksacks = rucksacks(input)
        assertEquals(7850, rucksacksPrioritySum(rucksacks))
    }

    @Test
    fun part2() {
        val elfGroups = elfGroups(input)
        assertEquals(2581, elfGroupsPrioritySum(elfGroups))
    }

}