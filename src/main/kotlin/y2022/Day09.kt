package y2022

import java.io.File
import kotlin.math.abs

enum class Direction { Up, Down, Left, Right }

data class Motion(val direction: Direction, val step: Int)

data class Position(val x: Int, val y: Int)

fun countTailPositions(motions: List<Motion>): Int {
    val positions = mutableSetOf<Position>()
    var head = Position(0, 0)
    var tail = Position(0, 0)
    for (motion in motions) {
        repeat(motion.step) {
            head = when (motion.direction) {
                Direction.Up -> head.copy(y = head.y + 1)
                Direction.Down -> head.copy(y = head.y - 1)
                Direction.Left -> head.copy(x = head.x - 1)
                Direction.Right -> head.copy(x = head.x + 1)
            }
            val xDistance = head.x - tail.x
            val yDistance = head.y - tail.y
            if (abs(xDistance) + abs(yDistance) > 2) {
                if (xDistance > 0) {
                    tail = tail.copy(x = tail.x + 1)
                }
                if (xDistance < 0) {
                    tail = tail.copy(x = tail.x - 1)
                }
                if (yDistance > 0) {
                    tail = tail.copy(y = tail.y + 1)
                }
                if (yDistance < 0) {
                    tail = tail.copy(y = tail.y - 1)
                }
            } else {
                if (abs(xDistance) > 1) {
                    if (xDistance > 0) {
                        tail = tail.copy(x = tail.x + 1)
                    }
                    if (xDistance < 0) {
                        tail = tail.copy(x = tail.x - 1)
                    }
                }
                if (abs(yDistance) > 1) {
                    if (yDistance > 0) {
                        tail = tail.copy(y = tail.y + 1)
                    }
                    if (yDistance < 0) {
                        tail = tail.copy(y = tail.y - 1)
                    }
                }
            }
            positions.add(tail)
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
