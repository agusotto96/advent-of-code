package y2022

import java.io.File
import kotlin.math.abs

enum class Direction { Up, Down, Left, Right }

data class Motion(val direction: Direction, val step: Int)

data class Position(var x: Int, var y: Int)

fun countTailPositions(motions: List<Motion>, ropeSize: Int): Int {
    val positions = mutableSetOf<Position>()
    val rope = List(ropeSize) { Position(0, 0) }
    val head = rope.first()
    val tail = rope.last()
    for (motion in motions) {
        repeat(motion.step) {
            moveHead(head, motion)
            rope.windowed(2).forEach { moveTail(it.first(), it.last()) }
            positions.add(tail.copy())
        }
    }
    return positions.count()
}

fun moveHead(head: Position, motion: Motion) {
    when (motion.direction) {
        Direction.Up -> head.y++
        Direction.Down -> head.y--
        Direction.Left -> head.x--
        Direction.Right -> head.x++
    }
}

fun moveTail(head: Position, tail: Position) {
    val xDistance = head.x - tail.x
    val yDistance = head.y - tail.y
    val absoluteDistance = abs(xDistance) + abs(yDistance)
    if (xDistance == 2 || (xDistance == 1 && absoluteDistance == 3)) tail.x++
    if (yDistance == 2 || (yDistance == 1 && absoluteDistance == 3)) tail.y++
    if (xDistance == -2 || (xDistance == -1 && absoluteDistance == 3)) tail.x--
    if (yDistance == -2 || (yDistance == -1 && absoluteDistance == 3)) tail.y--
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
