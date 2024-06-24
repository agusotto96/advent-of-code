package y2023

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day09 {

    private val input = File("src/test/resources/y2023/Day09.txt")

    @Test
    fun part1() {
        val histories = histories(input)
        val sum = historiesTotalSum(histories)
        assertEquals(1637452029, sum)
    }

}
