package y2022

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class Day08 {

    private val input = File("../inputs/202208.txt")

    @Test
    fun part1() {
        val treeGrid = treeGrid(input)
        assertEquals(1832, countVisibleTrees(treeGrid))
    }

    @Test
    fun part2() {
        val treeGrid = treeGrid(input)
        assertEquals(157320, maxScenicScore(treeGrid))
    }

}
