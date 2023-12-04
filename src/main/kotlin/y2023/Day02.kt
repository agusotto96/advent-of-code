package y2023

import java.io.File

data class Game(val id: Int, val sets: List<Map<Color, Int>>)

enum class Color {
    Red, Green, Blue
}

fun games(file: File): List<Game> {
    val games = mutableListOf<Game>()
    for (line in file.readLines()) {
        val splitLine = line.split(": ")
        val id = splitLine.first().removePrefix("Game ")
        val sets = mutableListOf<Map<Color, Int>>()
        for (set in splitLine.last().split("; ")) {
            val cubes = mutableMapOf<Color, Int>()
            for (cube in set.split(", ")) {
                val (count, color) = cube.split(" ")
                cubes[Color(color)!!] = count.toInt()
            }
            sets += cubes
        }
        val game = Game(id.toInt(), sets)
        games += game
    }
    return games
}

fun Color(color: String): Color? =
    when (color) {
        "red" -> Color.Red
        "green" -> Color.Green
        "blue" -> Color.Blue
        else -> null
    }

fun isPossibleGame(game: Game, bag: Map<Color, Int>): Boolean {
    for (set in game.sets) {
        for ((color, count) in set) {
            if (count > (bag[color] ?: 0)) {
                return false
            }
        }
    }
    return true
}

fun minimumBagPower(game: Game): Int {
    val bag = mutableMapOf<Color, Int>()
    for (set in game.sets) {
        for ((color, count) in set) {
            if (count > (bag[color] ?: 0)) {
                bag[color] = count
            }
        }
    }
    return bag.values.reduce { acc, c -> acc * c }
}
