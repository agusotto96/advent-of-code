package y2021

import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class Point(val x: Int, val y: Int)

data class Line(val start: Point, val end: Point)

fun isVertical(line: Line): Boolean =
    line.start.x == line.end.x

fun isHorizontal(line: Line): Boolean =
    line.start.y == line.end.y

fun isDiagonal(line: Line): Boolean =
    abs(abs(line.start.x) - abs(line.end.x)) == abs(abs(line.start.y) - abs(line.end.y))

fun overlappingPoints(lines: List<Line>, diagonals: Boolean = false): Int {
    val points = mutableSetOf<Point>()
    val overlappingPoints = mutableSetOf<Point>()
    for (line in lines) {
        for (point in path(line, diagonals)) {
            if (!points.add(point)) {
                overlappingPoints.add(point)
            }
        }
    }
    return overlappingPoints.size
}

fun path(line: Line, diagonals: Boolean): List<Point> =
    when {
        isHorizontal(line) -> horizontalPath(line)
        isVertical(line) -> verticalPath(line)
        isDiagonal(line) && diagonals -> diagonalPath(line)
        else -> emptyList()
    }

fun horizontalPath(line: Line): List<Point> {
    val y = line.start.y
    val start = min(line.start.x, line.end.x)
    val end = max(line.start.x, line.end.x)
    return (start..end).map { x -> Point(x, y) }
}

fun verticalPath(line: Line): List<Point> {
    val x = line.start.x
    val start = min(line.start.y, line.end.y)
    val end = max(line.start.y, line.end.y)
    return (start..end).map { y -> Point(x, y) }
}

fun diagonalPath(line: Line): List<Point> {
    val xPath = if (line.start.x < line.end.x) line.start.x..line.end.x else line.start.x downTo line.end.x
    val yPath = if (line.start.y < line.end.y) line.start.y..line.end.y else line.start.y downTo line.end.y
    return xPath.zip(yPath).map { pair -> Point(pair.first, pair.second) }
}

fun lines(file: File): List<Line> =
    file.readLines().map(::Line)

fun Line(input: String): Line {
    val points = input.split(" -> ").map(::Point)
    return Line(points.first(), points.last())
}

fun Point(input: String): Point {
    val pair = input.split(",").map(String::toInt)
    return Point(pair.first(), pair.last())
}