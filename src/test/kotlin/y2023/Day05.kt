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
        assertEquals(261668924, lowestLocation)
    }

    @Test
    fun part2() {
        val almanac = Almanac(input)
        val lowestLocation = almanac.seeds
            .asSequence()
            .chunked(2)
            .map { (start, length) -> Pair(start, start + length) }
            .flatMap { sourceRange(it, almanac.seedToSoil.map(::rangeToTriple)).map(::transform) }
            .flatMap { sourceRange(it, almanac.soilToFertilizer.map(::rangeToTriple)).map(::transform) }
            .flatMap { sourceRange(it, almanac.fertilizerToWater.map(::rangeToTriple)).map(::transform) }
            .flatMap { sourceRange(it, almanac.waterToLight.map(::rangeToTriple)).map(::transform) }
            .flatMap { sourceRange(it, almanac.lightToTemperature.map(::rangeToTriple)).map(::transform) }
            .flatMap { sourceRange(it, almanac.temperatureToHumidity.map(::rangeToTriple)).map(::transform) }
            .flatMap { sourceRange(it, almanac.humidityToLocation.map(::rangeToTriple)).map(::transform) }
            .map { (start, _) -> start }
            .min()
        assertEquals(24261545, lowestLocation)
    }

}
