package y2021

import java.io.File

fun depths(file: File): List<Int> =
    file.readLines().map(String::toInt)

fun depthIncrements(depths: List<Int>, windowSize: Int = 1): Int =
    depths.asSequence()
        .windowed(windowSize)
        .map { it.sum() }
        .windowed(2)
        .count { (a, b) -> b > a }
