package y2022

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class Day07 {

    private val input = File("src/test/resources/y2022/Day07.txt")

    @Test
    fun part1() {
        val outputLines = outputLines(input)
        assertEquals(1667443, directoriesSizeSum(outputLines, 100000))
    }

    @Test
    fun part2() {
        val outputLines = outputLines(input)
        assertEquals(8998590, smallestDeletableDirectorySize(outputLines, 70000000, 30000000))
    }

}
