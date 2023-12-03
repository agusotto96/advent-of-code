package y2023

import java.io.File

fun engineParts(file: File): List<List<EnginePart>> =
    file.readLines().map { it.map(::EnginePart) }

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
            val gearRatio = neighborPartNumbers.first() * neighborPartNumbers.last()
            gearRatios.add(gearRatio)
        }
    }
    return gearRatios
}

fun partNumberPositions(engineParts: List<List<EnginePart>>): List<Pair<Int, List<Pair<Int, Int>>>> {
    val symbolNeighborPositions = enginePartPositions<EnginePart.Symbol>(engineParts)
        .flatMap { (x, y) -> mooreNeighbors(x, y) }
    val numberPositions = numberPositions(engineParts)
    return numberPositions
        .filter { (_, positions) -> positions.any { it in symbolNeighborPositions } }
}

inline fun <reified T : EnginePart> enginePartPositions(engineParts: List<List<EnginePart>>): List<Pair<Int, Int>> {
    val positions = mutableListOf<Pair<Int, Int>>()
    for (y in engineParts.indices) {
        for (x in engineParts[y].indices) {
            val enginePart = engineParts[y][x]
            if (enginePart is T) {
                positions.add(x to y)
            }
        }
    }
    return positions
}

fun numberPositions(engineParts: List<List<EnginePart>>): List<Pair<Int, List<Pair<Int, Int>>>> {
    val numberPositions = mutableListOf<Pair<Int, List<Pair<Int, Int>>>>()
    for (y in engineParts.indices) {
        var digits = mutableListOf<EnginePart.Digit>()
        var positions = mutableListOf<Pair<Int, Int>>()
        for (x in engineParts[y].indices) {
            val enginePart = engineParts[y][x]
            if (enginePart is EnginePart.Digit) {
                digits.add(enginePart)
                positions.add(x to y)
            } else {
                if (digits.isNotEmpty()) {
                    val number = digits.map(EnginePart.Digit::value).fold(0) { acc, digit -> acc * 10 + digit }
                    numberPositions.add(number to positions)
                    digits = mutableListOf()
                    positions = mutableListOf()
                }
            }
        }
        if (digits.isNotEmpty()) {
            val number = digits.map(EnginePart.Digit::value).fold(0) { acc, digit -> acc * 10 + digit }
            numberPositions.add(number to positions)
        }
    }
    return numberPositions
}

fun mooreNeighbors(x: Int, y: Int): List<Pair<Int, Int>> {
    return listOf(
        x - 1 to y - 1,
        x - 1 to y,
        x - 1 to y + 1,
        x to y - 1,
        x to y + 1,
        x + 1 to y - 1,
        x + 1 to y,
        x + 1 to y + 1
    )
}

sealed interface EnginePart {
    data class Digit(val value: Int) : EnginePart
    object Period : EnginePart
    sealed interface Symbol : EnginePart {
        object Gear : Symbol
        object Normal : Symbol
    }
}

fun EnginePart(char: Char): EnginePart =
    when {
        char.isDigit() -> EnginePart.Digit(char.digitToInt())
        char == '.' -> EnginePart.Period
        char == '*' -> EnginePart.Symbol.Gear
        else -> EnginePart.Symbol.Normal
    }
