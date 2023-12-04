package y2023

import java.io.File

typealias Position = Pair<Int, Int>

sealed interface EnginePart {
    data class Digit(val value: Int) : EnginePart
    object Period : EnginePart
    sealed interface Symbol : EnginePart {
        object Gear : Symbol
        object Normal : Symbol
    }
}

fun engineParts(file: File): List<List<EnginePart>> =
    file.readLines().map { it.map(::EnginePart) }

fun EnginePart(char: Char): EnginePart =
    when {
        char.isDigit() -> EnginePart.Digit(char.digitToInt())
        char == '.' -> EnginePart.Period
        char == '*' -> EnginePart.Symbol.Gear
        else -> EnginePart.Symbol.Normal
    }

fun gearRatios(engineParts: List<List<EnginePart>>): List<Int> {
    val gearRatios = mutableListOf<Int>()
    val partNumberPositions = partNumberPositions(engineParts)
    val gearPositions = enginePartPositions<EnginePart.Symbol.Gear>(engineParts)
    for ((x, y) in gearPositions) {
        val gearNeighbors = mooreNeighbors(x, y)
        val neighborPartNumbers = partNumberPositions
            .filter { (_, positions) -> positions.any { it in gearNeighbors } }
            .map { (number, _) -> number }
        if (neighborPartNumbers.size == 2) {
            val gearRatio = neighborPartNumbers.first().toInt() * neighborPartNumbers.last().toInt()
            gearRatios += gearRatio
        }
    }
    return gearRatios
}

fun partNumberPositions(engineParts: List<List<EnginePart>>): List<Pair<Int, List<Position>>> {
    val symbolNeighborPositions = enginePartPositions<EnginePart.Symbol>(engineParts)
        .flatMap { (x, y) -> mooreNeighbors(x, y) }
    return engineParts
        .let(::numberPositions)
        .filter { (_, positions) -> positions.any { it in symbolNeighborPositions } }
}

inline fun <reified T : EnginePart> enginePartPositions(engineParts: List<List<EnginePart>>): List<Position> =
    engineParts.indices
        .flatMap { y -> engineParts[y].indices.map { x -> x to y } }
        .filter { (x, y) -> engineParts[y][x] is T }

fun numberPositions(engineParts: List<List<EnginePart>>): List<Pair<Int, List<Position>>> {
    val numberPositions = mutableListOf<Pair<Int, List<Position>>>()
    for (y in engineParts.indices) {
        var digits = mutableListOf<EnginePart.Digit>()
        var positions = mutableListOf<Position>()
        for (x in engineParts[y].indices) {
            val enginePart = engineParts[y][x]
            if (enginePart is EnginePart.Digit) {
                digits += enginePart
                positions += x to y
            } else {
                if (digits.isNotEmpty()) {
                    val number = digitsToInt(digits)
                    numberPositions += number to positions
                    digits = mutableListOf()
                    positions = mutableListOf()
                }
            }
        }
        if (digits.isNotEmpty()) {
            val number = digitsToInt(digits)
            numberPositions += number to positions
        }
    }
    return numberPositions
}

fun digitsToInt(digits: List<EnginePart.Digit>): Int =
    digits.map(EnginePart.Digit::value).fold(0) { acc, digit -> acc * 10 + digit }

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
