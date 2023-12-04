package y2021

import java.io.File
import kotlin.math.abs

fun positions(file: File): List<Int> =
    file.readText().split(",").map(String::toInt)

fun meanPosition(positions: List<Int>): Int =
    positions.sorted()[positions.size / 2]

fun averagePosition(positions: List<Int>) =
    positions.sum() / positions.size

fun constantFuelCost(from: Int, to: Int): Int =
    abs(from - to)

fun incrementalFuelCost(from: Int, to: Int): Int {
    val fuelCost = constantFuelCost(from, to)
    return fuelCost * (fuelCost + 1) / 2
}
