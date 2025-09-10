package y2019

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class Day07 {

    private val input = File("../inputs/201907.txt")

    @Test
    fun part1() {
        val intcodes = intcodes(input)
        var max = 0
        for (permutation in permutations(listOf(0, 1, 2, 3, 4))) {
            var input = 0
            for (phase in permutation) {
                input = run(intcodes.toMutableList(), null, null, phase, input)
            }
            if (input > max) {
                max = input
            }
        }
        assertEquals(38834, max)
    }

    @Test
    fun part2() {
        val intcodes = intcodes(input)
        var max = 0
        for (permutation in permutations(listOf(5, 6, 7, 8, 9))) {
            var input = 0
            for (phase in permutation) {
                input = run(intcodes.toMutableList(), null, null, phase, input)
            }
            if (input > max) {
                max = input
            }
        }
        assertEquals(38834, max)
    }

    fun permutations(elements: List<Int>): Sequence<List<Int>> = sequence {
        if (elements.size == 1) {
            yield(elements)
        } else {
            for (i in elements.indices) {
                val element = elements[i]
                val remaining = elements.take(i) + elements.drop(i + 1)
                for (perm in permutations(remaining)) {
                    yield(listOf(element) + perm)
                }
            }
        }
    }

}
