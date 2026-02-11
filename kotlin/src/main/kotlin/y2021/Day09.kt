package y2021

import java.io.File

typealias Heightmap = List<List<Int>>

typealias Location = Pair<Int, Int>

fun heightmap(file: File): Heightmap =
    file.readLines().map { it.map(Char::digitToInt) }

fun lowestHeightsRiskLevel(heightmap: Heightmap): Int =
    lowestHeights(heightmap).sumOf(::riskLevel)

fun multiplyBiggestBasinSizes(heightmap: Heightmap, times: Int): Int =
    basinSizes(heightmap)
        .sorted()
        .takeLast(times)
        .reduce(Int::times)

fun basinSizes(heightmap: Heightmap): List<Int> =
    lowestLocations(heightmap)
        .map { basin(heightmap, it) }
        .map(Set<Location>::size)

fun basin(heightmap: Heightmap, lowest: Location, visited: MutableSet<Location> = mutableSetOf(lowest)): Set<Location> {
    val up = up(lowest)
    val down = down(lowest)
    val left = left(lowest)
    val right = right(lowest)
    val upHeight = height(heightmap, up)
    val downHeight = height(heightmap, down)
    val leftHeight = height(heightmap, left)
    val rightHeight = height(heightmap, right)
    if (upHeight != null && upHeight < 9 && up !in visited) {
        visited.add(up)
        basin(heightmap, up, visited)
    }
    if (downHeight != null && downHeight < 9 && down !in visited) {
        visited.add(down)
        basin(heightmap, down, visited)
    }
    if (leftHeight != null && leftHeight < 9 && left !in visited) {
        visited.add(left)
        basin(heightmap, left, visited)
    }
    if (rightHeight != null && rightHeight < 9 && right !in visited) {
        visited.add(right)
        basin(heightmap, right, visited)
    }
    return visited
}

fun lowestHeights(heightmap: Heightmap): List<Int> =
    lowestLocations(heightmap).map { heightmap[it.first][it.second] }

fun riskLevel(height: Int): Int =
    height + 1

fun lowestLocations(heightmap: Heightmap): List<Location> {
    val lowestLocations = mutableListOf<Location>()
    for (i in heightmap.indices) {
        for (j in heightmap.first().indices) {
            val location = i to j
            val height = height(heightmap, location) ?: throw Exception()
            val up = height(heightmap, up(location))
            val down = height(heightmap, down(location))
            val left = height(heightmap, left(location))
            val right = height(heightmap, right(location))
            val adjacentHeights = setOfNotNull(up, down, left, right)
            val lowestAdjacentHeight = adjacentHeights.minOrNull()
            if (lowestAdjacentHeight != null && height < lowestAdjacentHeight) {
                lowestLocations.add(location)
            }
        }
    }
    return lowestLocations
}

private fun height(heightmap: Heightmap, location: Location): Int? =
    heightmap.getOrNull(location.first)?.getOrNull(location.second)

private fun up(location: Location): Location =
    location.first - 1 to location.second

private fun down(location: Location): Location =
    location.first + 1 to location.second

private fun left(location: Location): Location =
    location.first to location.second - 1

private fun right(location: Location): Location =
    location.first to location.second + 1
