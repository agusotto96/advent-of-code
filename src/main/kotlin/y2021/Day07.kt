package y2021

import java.io.File
import kotlin.math.abs

fun positions(file: File): List<Int> =
    file.readText().split(",").map(String::toInt)

fun positionsCount(positions: List<Int>): Map<Int, Int> =
    positions.groupingBy { it }.eachCount()

fun bestPositionFuelCost(positions: List<Int>, fuelCost: (Int, Int) -> Int): Int? {
    val possiblePositions = possiblePositions(positions)
    val count = positionsCount(positions)
    val fuelCosts = possiblePositions.map { position -> totalFuelCost(count, position, fuelCost) }
    return fuelCosts.minOrNull()
}

fun totalFuelCost(positionsCount: Map<Int, Int>, position: Int, fuelCost: (Int, Int) -> Int): Int =
    positionsCount.map { fuelCost(it.key, position) * it.value }.sum()

fun possiblePositions(positions: List<Int>): IntRange {
    val sortedPositions = positions.sorted()
    val min = sortedPositions.first()
    val max = sortedPositions.last()
    return min..max
}

fun constantFuelCost(from: Int, to: Int): Int =
    abs(from - to)

fun incrementalFuelCost(from: Int, to: Int): Int {
    val fuelCost = constantFuelCost(from, to)
    return fuelCost * (fuelCost + 1) / 2
}
