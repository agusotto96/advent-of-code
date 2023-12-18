package y2023

import java.io.File

data class Race(
    val time: Long,
    val distance: Long,
)

fun races(file: File): List<Race> {
    val lines = file.readLines()
    val times = lines.first().removePrefix("Time:").split(" ").filter(String::isNotBlank).map(String::toLong)
    val distances = lines.last().removePrefix("Distance:").split(" ").filter(String::isNotBlank).map(String::toLong)
    return times.zip(distances).map { (time, distance) -> Race(time, distance) }
}

fun race(file: File): Race {
    val lines = file.readLines()
    val time = lines.first().removePrefix("Time:").split(" ").filter(String::isNotBlank).joinToString("").let(String::toLong)
    val distance = lines.last().removePrefix("Distance:").split(" ").filter(String::isNotBlank).joinToString("").let(String::toLong)
    return Race(time, distance)
}

fun winningWays(race: Race): Long {
    for (hold in (0..race.time)) {
        val speed = hold
        val remainingTime = race.time - hold
        val distance = speed * remainingTime
        if (distance > race.distance) {
            return race.time + 1 - hold * 2
        }
    }
    return 0
}
