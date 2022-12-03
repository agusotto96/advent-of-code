package y2021

import java.io.File

fun depths(file: File): List<Int> {
    return file.readLines().map(String::toInt)
}

fun depths(file: File, windowSize: Int): List<Int> {
    return depths(file).windowed(windowSize).map(List<Int>::sum)
}

fun depthIncrease(depths: List<Int>): Int {
    var increased = 0
    var lastDepth: Int? = null
    for (depth in depths) {
        if (lastDepth != null && lastDepth < depth) {
            increased++
        }
        lastDepth = depth
    }
    return increased
}