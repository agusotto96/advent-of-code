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
            .map { transformSource(almanac.seedToSoil, it) }
            .map { transformSource(almanac.soilToFertilizer, it) }
            .map { transformSource(almanac.fertilizerToWater, it) }
            .map { transformSource(almanac.waterToLight, it) }
            .map { transformSource(almanac.lightToTemperature, it) }
            .map { transformSource(almanac.temperatureToHumidity, it) }
            .map { transformSource(almanac.humidityToLocation, it) }
            .min()
        assertEquals(35, lowestLocation)
    }

    @Test
    fun part2() {
        // TODO: BRUTE FORCE DOES NOT WORK ON REAL INPUT
        val almanac = Almanac(input)
        val seedRanges = almanac.seeds.chunked(2).flatMap { (start, length) -> (start until start + length) }
        val lowestLocation = seedRanges
            .asSequence()
            .map { transformSource(almanac.seedToSoil, it) }
            .map { transformSource(almanac.soilToFertilizer, it) }
            .map { transformSource(almanac.fertilizerToWater, it) }
            .map { transformSource(almanac.waterToLight, it) }
            .map { transformSource(almanac.lightToTemperature, it) }
            .map { transformSource(almanac.temperatureToHumidity, it) }
            .map { transformSource(almanac.humidityToLocation, it) }
            .min()
        assertEquals(46, lowestLocation)
    }

}
