package y2022

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day02 {

    private val input = File("src/test/resources/y2022/Day02.txt")

    @Test
    fun part1() {
        val assumedIndications = assumedIndications(input)
        assertEquals(8933, assumedIndicationsPoints(assumedIndications))
    }

    @Test
    fun part2() {
        val trueIndications = trueIndications(input)
        assertEquals(11998, trueIndicationsPoints(trueIndications))
    }

}
