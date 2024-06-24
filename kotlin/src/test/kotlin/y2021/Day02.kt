package y2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day02 {

    private val input = File("../inputs/202102.txt")

    @Test
    fun part1() {
        val instructions = instructions(input)
        assertEquals(1670340, simpleCourse(instructions))
    }

    @Test
    fun part2() {
        val instructions = instructions(input)
        assertEquals(1954293920, complexCourse(instructions))
    }

}
