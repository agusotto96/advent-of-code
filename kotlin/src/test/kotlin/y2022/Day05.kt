package y2022

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class Day05 {

    private val input = File("src/test/resources/y2022/Day05.txt")

    @Test
    fun part1() {
        val disarrangedCrane = DisarrangedCrane(input)
        val rearrangedCrane = completeRearrangement(disarrangedCrane, CraneModel.CrateMover9000)
        assertEquals("VQZNJMWTR".toList(), rearrangedCrane.topCrates())
    }

    @Test
    fun part2() {
        val disarrangedCrane = DisarrangedCrane(input)
        val rearrangedCrane = completeRearrangement(disarrangedCrane, CraneModel.CrateMover9001)
        assertEquals("NLCDCLVMQ".toList(), rearrangedCrane.topCrates())
    }

}
