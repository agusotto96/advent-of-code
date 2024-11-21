package y2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day03 {

    private val input = File("../inputs/202103.txt")

    @Test
    fun part1() {
        val report = report(input)
        assertEquals(2261546u, powerConsumption(report))
    }

    @Test
    fun part2() {
        val report = input.readLines()
        assertEquals(6775520, lifeSupportRating(report))
    }

}
