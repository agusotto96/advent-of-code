package y2022

import java.io.File
import kotlin.math.abs

enum class Direction { Up, Down, Left, Right }

data class Motion(val direction: Direction, val step: Int)

data class Position(var x: Int, var y: Int)

fun countTailPositions(motions: List<Motion>): Int {
    val positions = mutableSetOf<Position>()
    val head = Position(0, 0)
    val tail = Position(0, 0)
    for (motion in motions) {
        repeat(motion.step) {
            when (motion.direction) {
                Direction.Up -> head.y++
                Direction.Down -> head.y--
                Direction.Left -> head.x--
                Direction.Right -> head.x++
            }
            val xDistance = head.x - tail.x
            val yDistance = head.y - tail.y
            val absoluteDistance = abs(xDistance) + abs(yDistance)
            if (xDistance == 2 || (xDistance == 1 && absoluteDistance == 3)) tail.x++
            if (yDistance == 2 || (yDistance == 1 && absoluteDistance == 3)) tail.y++
            if (xDistance == -2 || (xDistance == -1 && absoluteDistance == 3)) tail.x--
            if (yDistance == -2 || (yDistance == -1 && absoluteDistance == 3)) tail.y--
            positions.add(tail.copy())
        }
    }
    return positions.count()
}

fun motions(file: File): List<Motion> =
    file.readLines()
        .map { Motion(Direction(it.first()), it.split(" ").last().toInt()) }

fun Direction(symbol: Char): Direction =
    when (symbol) {
        'U' -> Direction.Up
        'D' -> Direction.Down
        'L' -> Direction.Left
        'R' -> Direction.Right
        else -> throw Exception()
    }
