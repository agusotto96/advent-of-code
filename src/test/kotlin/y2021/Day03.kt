package y2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day03 {

    private val input = File("src/test/resources/y2021/Day03.txt")

    @Test
    fun part1() {
        val report = report(input)
        assertEquals(2261546, powerConsumption(report))
    }

    @Test
    fun part2() {
        val report = report(input)
        assertEquals(6775520, lifeSupportRating(report))
    }

}
