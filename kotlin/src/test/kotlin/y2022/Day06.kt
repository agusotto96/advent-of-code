package y2022

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

internal class Day06 {

    private val input = File("src/test/resources/y2022/Day06.txt")

    @Test
    fun part1() {
        val signal = signal(input)
        assertEquals(1198, countProcessedSignal(signal, Marker.StartOfPacket))
    }


    @Test
    fun part2() {
        val signal = signal(input)
        assertEquals(3120, countProcessedSignal(signal, Marker.StartOfMessage))
    }

}
