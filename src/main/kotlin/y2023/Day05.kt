package y2023

import java.io.File

data class Almanac(
    val seeds: List<Long>,
    val seedRanges: List<SourceRange>,
    val seedToSoil: List<DestinationRange>,
    val soilToFertilizer: List<DestinationRange>,
    val fertilizerToWater: List<DestinationRange>,
    val waterToLight: List<DestinationRange>,
    val lightToTemperature: List<DestinationRange>,
    val temperatureToHumidity: List<DestinationRange>,
    val humidityToLocation: List<DestinationRange>,
)

data class SourceRange(
    val start: Long,
    val end: Long,
)

data class DestinationRange(
    val start: Long,
    val end: Long,
    val factor: Long,
)

fun Almanac(file: File): Almanac {
    val almanacSections = file.readText().split("\n\n")
    if (almanacSections.size != 8) {
        throw Exception("invalid section count")
    }
    val seeds = almanacSections[0].removePrefix("seeds: ").split(" ").map(String::toLong)
    val seedRanges = sourceRanges(seeds)
    val seedToSoil = almanacSections[1].removePrefix("seed-to-soil map:\n").let(::destinationRanges)
    val soilToFertilizer = almanacSections[2].removePrefix("soil-to-fertilizer map:\n").let(::destinationRanges)
    val fertilizerToWater = almanacSections[3].removePrefix("fertilizer-to-water map:\n").let(::destinationRanges)
    val waterToLight = almanacSections[4].removePrefix("water-to-light map:\n").let(::destinationRanges)
    val lightToTemperature = almanacSections[5].removePrefix("light-to-temperature map:\n").let(::destinationRanges)
    val temperatureToHumidity = almanacSections[6].removePrefix("temperature-to-humidity map:\n").let(::destinationRanges)
    val humidityToLocation = almanacSections[7].removePrefix("humidity-to-location map:\n").let(::destinationRanges)
    return Almanac(
        seeds,
        seedRanges,
        seedToSoil,
        soilToFertilizer,
        fertilizerToWater,
        waterToLight,
        lightToTemperature,
        temperatureToHumidity,
        humidityToLocation
    )
}

fun sourceRanges(sources: List<Long>): List<SourceRange> =
    sources.chunked(2).map { (start, length) -> SourceRange(start, start + length) }

fun destinationRanges(section: String): List<DestinationRange> =
    section
        .split("\n")
        .filter(String::isNotBlank)
        .map { it.split(" ").map(String::toLong) }
        .map { (destinationStart, sourceStart, length) ->
            DestinationRange(sourceStart, sourceStart + length, destinationStart - sourceStart)
        }

fun transform(source: Long, destinationRanges: List<DestinationRange>): Long {
    for (destinationRange in destinationRanges) {
        if (source >= destinationRange.start && source <= destinationRange.end) {
            return source + destinationRange.factor
        }
    }
    return source
}

fun transform(sourceRange: SourceRange, destinationRanges: List<DestinationRange>): List<SourceRange> {

    // FIND INTERSECTIONS
    val intersections = mutableListOf<DestinationRange>()
    for (destinationRange in destinationRanges) {
        val intersectionStart = maxOf(sourceRange.start, destinationRange.start)
        val intersectionEnd = minOf(sourceRange.end, destinationRange.end)
        if (intersectionStart > intersectionEnd) {
            continue
        }
        val intersection = DestinationRange(intersectionStart, intersectionEnd, destinationRange.factor)
        intersections.add(intersection)
    }
    if (intersections.isEmpty()) {
        return listOf(SourceRange(sourceRange.start, sourceRange.end))
    }

    // FILL GAPS WITH SOURCE
    val gaps = mutableListOf<SourceRange>()
    intersections.sortBy { (start, _, _) -> start }
    if (sourceRange.start < intersections.first().start) {
        val gap = SourceRange(sourceRange.start, intersections.first().start - 1)
        gaps.add(gap)
    }
    if (sourceRange.end > intersections.last().end) {
        val intersection = DestinationRange(intersections.last().end + 1, sourceRange.end, 0L)
        intersections.add(intersection)
    }
    for ((a, b) in intersections.windowed(2)) {
        if (b.start - a.end > 1) {
            val gap = SourceRange(a.end + 1, b.start - 1)
            gaps.add(gap)
        }
    }

    // RETURN TRANSFORMED INTERSECTIONS AND SOURCE GAPS
    return intersections.map { SourceRange(it.start + it.factor, it.end + it.factor) } + gaps

}
