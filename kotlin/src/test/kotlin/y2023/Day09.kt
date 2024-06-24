package y2023

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day09 {

    private val input = File("../inputs/202309.txt")

    @Test
    fun part1() {
        val histories = histories(input)
        val sum = historiesTotalSum(histories)
        assertEquals(1637452029, sum)
    }

}
