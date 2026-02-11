package y2021

fun depths(lines: Sequence<String>): Sequence<Int> =
    lines.map(String::toInt)

fun depthIncrements(depths: Sequence<Int>, windowSize: Int = 1): Int =
    depths.windowed(windowSize)
        .map { it.sum() }
        .windowed(2)
        .count { (a, b) -> b > a }
