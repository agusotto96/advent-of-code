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

/////////////////////////////////////////////

fun sourceRange(
    sourceRange: Pair<Long, Long>,
    destinationRanges: List<Triple<Long, Long, Long>>,
): List<Triple<Long, Long, Long>> {

    // FIND INTERSECTIONS
    val (sourceStart, sourceEnd) = sourceRange
    val intersections = mutableListOf<Triple<Long, Long, Long>>()
    for ((destinationStart, destinationEnd, factor) in destinationRanges) {
        val (intersectionStart, intersectionEnd) = intersection(sourceStart, sourceEnd, destinationStart, destinationEnd) ?: continue
        val intersection = Triple(intersectionStart, intersectionEnd, factor)
        intersections.add(intersection)
    }
    if (intersections.isEmpty()) {
        return listOf(Triple(sourceStart, sourceEnd, 0L))
    }

    // FILL GAPS WITH SOURCE
    intersections.sortBy { (start, _, _) -> start }
    if (sourceStart < intersections.first().first) {
        val intersection = Triple(sourceStart, intersections.first().first - 1, 0L)
        intersections.add(intersection)
    }
    if (sourceEnd > intersections.last().second) {
        val intersection = Triple(intersections.last().second + 1, sourceEnd, 0L)
        intersections.add(intersection)
    }
    for ((a, b) in intersections.windowed(2)) {
        if (b.first - a.second > 1) {
            val intersection = Triple(a.second + 1, b.first - 1, 0L)
            intersections.add(intersection)
        }
    }
    return intersections
}

fun transform(destinationRange: Triple<Long, Long, Long>): Pair<Long, Long> {
    val (destinationStart, destinationEnd, factor) = destinationRange
    val sourceStart = destinationStart + factor
    val sourceEnd = destinationEnd + factor
    return Pair(sourceStart, sourceEnd)
}

fun intersection(sourceStart: Long, sourceEnd: Long, destinationStart: Long, destinationEnd: Long): Pair<Long, Long>? {
    val start = maxOf(sourceStart, destinationStart)
    val end = minOf(sourceEnd, destinationEnd)
    if (start > end) {
        return null
    }
    return Pair(start, end)
}

fun rangeToTriple(range: Range): Triple<Long, Long, Long> {
    return Triple(range.sourceStart, range.sourceStart + range.length, range.destinationStart - range.sourceStart)
}
