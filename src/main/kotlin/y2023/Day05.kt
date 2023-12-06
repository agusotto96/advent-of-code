package y2023

import java.io.File

fun Almanac(file: File): Almanac {
    val almanacSections = file.readText().split("\n\n")
    if (almanacSections.size != 8) {
        throw Exception("invalid section count")
    }
    val seeds = almanacSections[0].removePrefix("seeds: ").split(" ").map(String::toLong)
    val seedToSoil = almanacSections[1].removePrefix("seed-to-soil map:\n").let(::ranges)
    val soilToFertilizer = almanacSections[2].removePrefix("soil-to-fertilizer map:\n").let(::ranges)
    val fertilizerToWater = almanacSections[3].removePrefix("fertilizer-to-water map:\n").let(::ranges)
    val waterToLight = almanacSections[4].removePrefix("water-to-light map:\n").let(::ranges)
    val lightToTemperature = almanacSections[5].removePrefix("light-to-temperature map:\n").let(::ranges)
    val temperatureToHumidity = almanacSections[6].removePrefix("temperature-to-humidity map:\n").let(::ranges)
    val humidityToLocation = almanacSections[7].removePrefix("humidity-to-location map:\n").let(::ranges)
    return Almanac(
        seeds,
        seedToSoil,
        soilToFertilizer,
        fertilizerToWater,
        waterToLight,
        lightToTemperature,
        temperatureToHumidity,
        humidityToLocation
    )
}

fun ranges(section: String): List<Range> =
    section
        .split("\n")
        .filter(String::isNotBlank)
        .map { it.split(" ").map(String::toLong) }
        .map { (destinationStart, sourceStart, length) -> Range(destinationStart, sourceStart, length) }

fun transformSource(ranges: List<Range>, source: Long): Long {
    for (range in ranges) {
        val (destinationStart, sourceStart, length) = range
        if (source >= sourceStart && source < sourceStart + length) {
            return destinationStart + (source - sourceStart)
        }
    }
    return source
}

data class Almanac(
    val seeds: List<Long>,
    val seedToSoil: List<Range>,
    val soilToFertilizer: List<Range>,
    val fertilizerToWater: List<Range>,
    val waterToLight: List<Range>,
    val lightToTemperature: List<Range>,
    val temperatureToHumidity: List<Range>,
    val humidityToLocation: List<Range>,
)

data class Range(
    val destinationStart: Long,
    val sourceStart: Long,
    val length: Long,
)
