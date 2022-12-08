package y2022

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class Day07 {

    private val input = File("src/test/resources/y2022/Day07.txt")

    @Test
    fun part1() {
        val outputLines = outputLines(input)
        assertEquals(95437, asd(outputLines, 100000))
    }

}
