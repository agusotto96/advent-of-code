package y2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day02 {

    private val input = File("src/test/resources/y2022/Day02.txt")

    @Test
    fun part1() {
        val rawIndications = rawIndications(input)
        val assumedIndications = assumedIndications(rawIndications)
        assertEquals(8933, assumedIndicationsPoints(assumedIndications))
    }

    @Test
    fun part2() {
        val rawIndications = rawIndications(input)
        val trueIndications = trueIndications(rawIndications)
        assertEquals(11998, trueIndicationsPoints(trueIndications))
    }

}