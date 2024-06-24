package y2021

import java.io.File
import kotlin.math.abs

fun positions(file: File): List<Int> =
    file.readText().split(",").map(String::toInt)

fun meanPosition(positions: List<Int>): Int =
    positions.sorted()[positions.size / 2]

fun positionRange(positions: List<Int>): Pair<Int, Int>? {
    var min: Int? = null
    var max: Int? = null
    for (position in positions) {
        if (min == null || position < min) {
            min = position
        }
        if (max == null || position > max) {
            max = position
        }
    }
    if (min != null && max != null) {
        return min to max
    }
    return null
}

fun constantFuelCost(from: Int, to: Int): Int =
    abs(from - to)

fun incrementalFuelCost(from: Int, to: Int): Int {
    val fuelCost = constantFuelCost(from, to)
    return fuelCost * (fuelCost + 1) / 2
}
