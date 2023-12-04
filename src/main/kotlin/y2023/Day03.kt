package y2023

import java.io.File

typealias Position = Pair<Int, Int>

data class EngineSchematic(
    val symbolPositions: List<Position>,
    val gearPositions: List<Position>,
    val numberPositions: List<Pair<Int, List<Position>>>,
)

fun EngineSchematic(file: File): EngineSchematic {
    val symbolPositions = mutableListOf<Position>()
    val gearPositions = mutableListOf<Position>()
    val digitPositions = mutableListOf<Pair<Int, Position>>()
    for ((y, line) in file.readLines().withIndex()) {
        for ((x, char) in line.withIndex()) {
            when {
                char.isDigit() -> {
                    digitPositions += char.digitToInt() to (x to y)
                }
                char == '*' -> {
                    gearPositions += x to y
                    symbolPositions += x to y
                }
                char != '.' -> {
                    symbolPositions += x to y
                }
            }
        }
    }
    val numberPositions = numberPositions(digitPositions)
    return EngineSchematic(symbolPositions, gearPositions, numberPositions)
}

fun partNumbers(engineSchematic: EngineSchematic): List<Int> {
    val (symbolPositions, _, numberPositions) = engineSchematic
    val symbolNeighborPositions = symbolPositions
        .flatMap { (x, y) -> mooreNeighbors(x, y) }
        .toSet()
    return numberPositions
        .filter { (_, positions) -> positions.any { it in symbolNeighborPositions } }
        .map { (number, _) -> number }
}

fun gearRatios(engineSchematic: EngineSchematic): List<Int> {
    val (_, gearPositions, numberPositions) = engineSchematic
    val numbersByPosition = numberPositions
        .flatMapIndexed { index, (number, positions) -> positions.map { position -> position to (number to index) } }
        .toMap()
    return gearPositions
        .asSequence()
        .map { (x, y) -> mooreNeighbors(x, y) }
        .map { it.mapNotNull(numbersByPosition::get).distinctBy { (_, index) -> index }.map { (number, _) -> number } }
        .filter { it.size == 2 }
        .map { it.first() * it.last() }
        .toList()
}

fun numberPositions(digitPositions: List<Pair<Int, Position>>): List<Pair<Int, List<Position>>> {
    val numberPositions = mutableListOf<Pair<Int, List<Position>>>()
    var number = 0
    var positions = mutableListOf<Position>()
    for ((digit, position) in digitPositions) {
        if (positions.isNotEmpty()) {
            val (x, y) = position
            val (lastX, lastY) = positions.last()
            if (lastX + 1 != x || lastY != y) {
                numberPositions += number to positions
                number = 0
                positions = mutableListOf()
            }
        }
        number = (number * 10) + digit
        positions += position
    }
    if (positions.isNotEmpty()) {
        numberPositions += number to positions
    }
    return numberPositions
}

fun mooreNeighbors(x: Int, y: Int): List<Position> =
    listOf(
        x - 1 to y - 1,
        x - 1 to y,
        x - 1 to y + 1,
        x to y - 1,
        x to y + 1,
        x + 1 to y - 1,
        x + 1 to y,
        x + 1 to y + 1
    )
