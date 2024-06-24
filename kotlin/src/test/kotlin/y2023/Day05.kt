package y2023

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

internal class Day05 {

    private val input = File("src/test/resources/y2023/Day05.txt")

    @Test
    fun part1() {
        val almanac = Almanac(input)
        val lowestLocation = almanac.seeds
            .asSequence()
            .map { transform(it, almanac.seedToSoil) }
            .map { transform(it, almanac.soilToFertilizer) }
            .map { transform(it, almanac.fertilizerToWater) }
            .map { transform(it, almanac.waterToLight) }
            .map { transform(it, almanac.lightToTemperature) }
            .map { transform(it, almanac.temperatureToHumidity) }
            .map { transform(it, almanac.humidityToLocation) }
            .min()
        assertEquals(261668924, lowestLocation)
    }

    @Test
    fun part2() {
        val almanac = Almanac(input)
        val lowestLocation = almanac.seedRanges
            .asSequence()
            .flatMap { transform(it, almanac.seedToSoil) }
            .flatMap { transform(it, almanac.soilToFertilizer) }
            .flatMap { transform(it, almanac.fertilizerToWater) }
            .flatMap { transform(it, almanac.waterToLight) }
            .flatMap { transform(it, almanac.lightToTemperature) }
            .flatMap { transform(it, almanac.temperatureToHumidity) }
            .flatMap { transform(it, almanac.humidityToLocation) }
            .map { (start, _) -> start }
            .min()
        assertEquals(24261545, lowestLocation)
    }

}
